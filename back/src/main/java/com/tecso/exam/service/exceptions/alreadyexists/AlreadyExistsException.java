package com.tecso.exam.service.exceptions.alreadyexists;

public class AlreadyExistsException extends IllegalArgumentException {
    public AlreadyExistsException(String entityName) {
        super(entityName + " with provided id already exists");
    }
}
