package com.nicole.shaine.test.task.service.impl;

import com.nicole.shaine.test.task.dao.abs.ClientDao;
import com.nicole.shaine.test.task.models.entitys.Client;
import com.nicole.shaine.test.task.service.abs.AbstractServiceImpl;
import com.nicole.shaine.test.task.service.abs.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends AbstractServiceImpl<Long, Client> implements ClientService {

    @Autowired
    public ClientServiceImpl(ClientDao clientDao) {
        super(clientDao);
    }


}
