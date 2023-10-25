package com.nicole.shaine.test.task.models.Exceptions.abs;

public abstract class RequestParamException extends RuntimeException {
    public RequestParamException() {
        super();
    }

    public RequestParamException(String errorMessage) {
        super(errorMessage);
    }
}
