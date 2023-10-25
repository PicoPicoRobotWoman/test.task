package com.nicole.shaine.test.task.models.Exceptions.impl;

import com.nicole.shaine.test.task.models.Exceptions.abs.RequestParamException;

public class RequestBodyParamException extends RequestParamException {

    public RequestBodyParamException() {
        super();
    }

    public RequestBodyParamException(String errorMessage) {
        super(errorMessage);
    }

}
