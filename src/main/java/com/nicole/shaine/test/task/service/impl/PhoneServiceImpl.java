package com.nicole.shaine.test.task.service.impl;

import com.nicole.shaine.test.task.dao.abs.PhoneDao;
import com.nicole.shaine.test.task.models.entitys.Phone;
import com.nicole.shaine.test.task.service.abs.AbstractServiceImpl;
import com.nicole.shaine.test.task.service.abs.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PhoneServiceImpl extends AbstractServiceImpl<Long, Phone> implements PhoneService {

    private final PhoneDao phoneDao;

    @Autowired
    public PhoneServiceImpl(PhoneDao phoneDao) {
        super(phoneDao);
        this.phoneDao = phoneDao;
    }

    @Override
    public Boolean isExistByValues(Set<String> values) {

        return phoneDao.isExistByValues(values);
    }
}
