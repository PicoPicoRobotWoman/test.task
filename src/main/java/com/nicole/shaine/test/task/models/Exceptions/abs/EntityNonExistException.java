package com.nicole.shaine.test.task.models.Exceptions.abs;

public abstract class EntityNonExistException extends RuntimeException {

    public EntityNonExistException() {
        super();
    }

    public EntityNonExistException(String errorMessage) {
        super(errorMessage);
    }

}
