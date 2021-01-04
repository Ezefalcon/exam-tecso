package com.tecso.exam.service.exceptions.notfound;

public class NotFoundException extends IllegalArgumentException {
    public NotFoundException(String entityName) {
        super(entityName + " with the specified id does not exists");
    }
}
