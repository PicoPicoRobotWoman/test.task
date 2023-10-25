package com.nicole.shaine.test.task.models.Exceptions.impl;

import com.nicole.shaine.test.task.models.Exceptions.abs.EntityExistException;

public class ContactExistException extends EntityExistException {

    public ContactExistException(String errorMessage) {
        super(errorMessage);
    }

}
