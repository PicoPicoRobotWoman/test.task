package com.nicole.shaine.test.task.models.Exceptions.impl;

import com.nicole.shaine.test.task.models.Exceptions.abs.RequestParamException;

public class RequestPathVariableException extends RequestParamException {
    public RequestPathVariableException() {
        super();
    }

    public RequestPathVariableException(String errorMessage) {
        super(errorMessage);
    }
}
