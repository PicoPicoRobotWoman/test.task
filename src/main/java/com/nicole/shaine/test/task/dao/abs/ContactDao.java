package com.nicole.shaine.test.task.dao.abs;

import com.nicole.shaine.test.task.models.entitys.Contact;
import com.nicole.shaine.test.task.models.enums.ContactType;

import java.util.List;
import java.util.Set;


public interface ContactDao extends AbstractDao<Long, Contact> {

    Boolean isExistByValues(Set<String> Values);
    List<Contact> getByClientIdAndContactTypes(Long id, Set<ContactType> contactTypes);
}


