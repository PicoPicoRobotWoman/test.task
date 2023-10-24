package com.nicole.shaine.test.task.models.Exceptions.impl;

import com.nicole.shaine.test.task.models.Exceptions.abs.EntityExistException;

public class EmailExistException extends EntityExistException {

    public EmailExistException(String errorMessage) {
        super(errorMessage);
    }

}
