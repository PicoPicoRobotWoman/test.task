package com.nicole.shaine.test.task.dao.impl;

import com.nicole.shaine.test.task.dao.abs.AbstractDaoImpl;
import com.nicole.shaine.test.task.dao.abs.PhoneDao;
import com.nicole.shaine.test.task.models.entitys.Phone;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class PhoneDaoImpl extends AbstractDaoImpl<Long, Phone> implements PhoneDao {
    @Override
    public List<Phone> getAll() {

        String jpql = "SELECT phone FROM Phone phone";
        return entityManager.createQuery(jpql, Phone.class)
            .getResultList();
    }

    @Override
    public Boolean isExistByValues(Set<String> values) {

        if(values == null || values.isEmpty()) {
            return false;
        }

        String jpql = "SELECT COUNT(phone) FROM Phone phone WHERE phone.value IN :values";
        Long count = entityManager.createQuery(jpql, Long.class)
            .setParameter("values", values)
            .getSingleResult();

        return count > 0;
    }
}
