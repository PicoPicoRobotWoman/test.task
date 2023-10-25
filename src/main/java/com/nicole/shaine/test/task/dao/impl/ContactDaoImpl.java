package com.nicole.shaine.test.task.dao.impl;

import com.nicole.shaine.test.task.dao.abs.AbstractDaoImpl;
import com.nicole.shaine.test.task.dao.abs.ContactDao;
import com.nicole.shaine.test.task.models.entitys.Contact;
import com.nicole.shaine.test.task.models.enums.ContactType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class ContactDaoImpl extends AbstractDaoImpl<Long, Contact> implements ContactDao {
    @Override
    public List<Contact> getAll() {
        String jpql = "SELECT contact FROM Contact contact";
        return entityManager.createQuery(jpql, Contact.class)
            .getResultList();
    }

    @Override
    public Boolean isExistByValues(Set<String> values) {

        if(values == null || values.isEmpty()) {
            return false;
        }

        String jpql = "SELECT COUNT(contact) FROM Contact contact WHERE contact.value IN :values";
        Long count = entityManager.createQuery(jpql, Long.class)
            .setParameter("values", values)
            .getSingleResult();

        return count > 0;

    }

    @Override
    public List<Contact> getByClientIdAndContactTypes(Long id, Set<ContactType> contactTypes) {
        String jpql = "SELECT contact FROM Contact contact WHERE contact.client.id = :id AND contact.contactType IN :contactTypes";
        return entityManager.createQuery(jpql, Contact.class)
            .setParameter("id", id)
            .setParameter("contactTypes", contactTypes)
            .getResultList();
    }
    }
