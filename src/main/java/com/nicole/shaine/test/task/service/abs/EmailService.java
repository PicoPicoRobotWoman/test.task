package com.nicole.shaine.test.task.service.abs;

import com.nicole.shaine.test.task.models.entitys.Email;

import java.util.Set;

public interface EmailService extends AbstractService<Long, Email> {

    Boolean isExistByAddresses(Set<String> addresses);
}
