package com.nicole.shaine.test.task.service.abs;

import com.nicole.shaine.test.task.models.entitys.Contact;
import com.nicole.shaine.test.task.models.enums.ContactType;

import java.util.Set;

public interface ContactService extends AbstractService<Long, Contact> {

    Boolean isExistByValues(Set<String> values);
    Set<Contact> getByClientIdAndContactType(Long id, ContactType contactType);

}
