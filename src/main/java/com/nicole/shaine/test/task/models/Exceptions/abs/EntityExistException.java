package com.nicole.shaine.test.task.models.Exceptions.abs;

public abstract class EntityExistException extends RuntimeException {

    public EntityExistException() {
        super();
    }

    public EntityExistException(String errorMessage) {
        super(errorMessage);
    }

}
