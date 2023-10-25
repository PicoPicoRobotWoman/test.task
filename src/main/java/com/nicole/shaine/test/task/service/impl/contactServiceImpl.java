package com.nicole.shaine.test.task.service.impl;

import com.nicole.shaine.test.task.dao.abs.ContactDao;
import com.nicole.shaine.test.task.models.entitys.Contact;
import com.nicole.shaine.test.task.models.enums.ContactType;
import com.nicole.shaine.test.task.service.abs.AbstractServiceImpl;
import com.nicole.shaine.test.task.service.abs.contactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class contactServiceImpl extends AbstractServiceImpl<Long, Contact> implements contactService {

    private final ContactDao contactDao;

    @Autowired
    public contactServiceImpl(ContactDao contactDao) {
        super(contactDao);
        this.contactDao = contactDao;
    }

    @Override
    public Boolean isExistByValues(Set<String> values) {
        return contactDao.isExistByValues(values);
    }

    @Override
    public Set<Contact> getByClientIdAndContactType(Long id, ContactType contactType) {
        Set<ContactType> contactTypes = contactType == null
            ? Arrays.stream(ContactType.values()).collect(Collectors.toSet())
            : Collections.singleton(contactType);

        return new HashSet<>(contactDao.getByClientIdAndContactTypes(id, contactTypes));
    }
}
