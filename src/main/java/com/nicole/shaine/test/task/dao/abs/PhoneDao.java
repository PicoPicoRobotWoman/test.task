package com.nicole.shaine.test.task.dao.abs;

import com.nicole.shaine.test.task.models.entitys.Phone;

import java.util.Set;

public interface PhoneDao extends AbstractDao<Long, Phone> {

    Boolean isExistByValues(Set<String> values);
}
