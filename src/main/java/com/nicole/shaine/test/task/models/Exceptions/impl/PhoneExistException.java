package com.nicole.shaine.test.task.models.Exceptions.impl;

import com.nicole.shaine.test.task.models.Exceptions.abs.EntityExistException;

public class PhoneExistException extends EntityExistException {

    public PhoneExistException(String errorMessage) {
        super(errorMessage);
    }

}
