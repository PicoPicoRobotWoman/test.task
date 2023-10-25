package com.nicole.shaine.test.task.dao.impl;

import com.nicole.shaine.test.task.dao.abs.AbstractDaoImpl;
import com.nicole.shaine.test.task.dao.abs.ClientDao;
import com.nicole.shaine.test.task.models.entitys.Client;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientDaoImpl extends AbstractDaoImpl<Long, Client> implements ClientDao {
    @Override
    public Optional<Client> getById(Long id) {
        String jpql = "SELECT client FROM Client client LEFT JOIN FETCH client.contacts WHERE client.id = :id";
        return Optional.ofNullable(entityManager.createQuery(jpql, Client.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    @Override
    public List<Client> getAll() {
        String jpql = "SELECT DISTINCT client FROM Client client LEFT JOIN FETCH client.contacts ";
        return entityManager.createQuery(jpql, Client.class)
            .getResultList();
    }

}
