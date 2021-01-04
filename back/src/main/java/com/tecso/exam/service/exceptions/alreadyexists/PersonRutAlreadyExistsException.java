package com.tecso.exam.service.exceptions.alreadyexists;

public class PersonRutAlreadyExistsException extends IllegalArgumentException {
    public PersonRutAlreadyExistsException() {
        super("Person with same RUT already exists");
    }
}
