package com.nicole.shaine.test.task.models.Exceptions.impl;

import com.nicole.shaine.test.task.models.Exceptions.abs.EntityNonExistException;

public class ClientNonExistException extends EntityNonExistException {

    public ClientNonExistException(String errorMessage) {
        super(errorMessage);
    }

}
