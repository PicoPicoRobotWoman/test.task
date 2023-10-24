package com.nicole.shaine.test.task.dao.impl;

import com.nicole.shaine.test.task.dao.abs.AbstractDaoImpl;
import com.nicole.shaine.test.task.dao.abs.ClientDao;
import com.nicole.shaine.test.task.models.entitys.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDaoImpl extends AbstractDaoImpl<Long, Client> implements ClientDao {
    @Override
    public List<Client> getAll() {
        String jpql = "SELECT client FROM Client client";
        return entityManager.createQuery(jpql, Client.class)
            .getResultList();
    }

}
