package com.nicole.shaine.test.task.dao.abs;

import com.nicole.shaine.test.task.models.entitys.Email;

import java.util.Set;

public interface EmailDao extends AbstractDao<Long, Email> {

    Boolean isExistByAddresses(Set<String> addresses);
}
