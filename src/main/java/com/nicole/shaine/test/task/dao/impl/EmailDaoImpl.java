package com.nicole.shaine.test.task.dao.impl;

import com.nicole.shaine.test.task.dao.abs.AbstractDaoImpl;
import com.nicole.shaine.test.task.dao.abs.EmailDao;
import com.nicole.shaine.test.task.models.entitys.Email;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class EmailDaoImpl extends AbstractDaoImpl<Long, Email> implements EmailDao {
    @Override
    public List<Email> getAll() {
        String jpql = "SELECT email FROM Email email";
        return entityManager.createQuery(jpql, Email.class)
            .getResultList();
    }

    @Override
    public Boolean isExistByAddresses(Set<String> addresses) {

        if(addresses == null || addresses.isEmpty()) {
            return false;
        }

        String jpql = "SELECT COUNT(email) FROM Email email WHERE email.address IN :addresses";
        Long count = entityManager.createQuery(jpql, Long.class)
            .setParameter("addresses", addresses)
            .getSingleResult();

        return count > 0;


    }
}
