package com.nicole.shaine.test.task.service.impl;

import com.nicole.shaine.test.task.dao.abs.EmailDao;
import com.nicole.shaine.test.task.models.entitys.Email;
import com.nicole.shaine.test.task.service.abs.AbstractServiceImpl;
import com.nicole.shaine.test.task.service.abs.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EmailServiceImpl extends AbstractServiceImpl<Long, Email> implements EmailService {

    private final EmailDao emailDao;

    @Autowired
    public EmailServiceImpl(EmailDao emailDao) {
        super(emailDao);
        this.emailDao = emailDao;
    }

    @Override
    public Boolean isExistByAddresses(Set<String> addresses) {
        return emailDao.isExistByAddresses(addresses);
    }
}
