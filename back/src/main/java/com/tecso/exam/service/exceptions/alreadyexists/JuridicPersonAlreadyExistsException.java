package com.tecso.exam.service.exceptions.alreadyexists;

public class JuridicPersonAlreadyExistsException extends IllegalArgumentException {
    public JuridicPersonAlreadyExistsException() {
        super("Juridic person");
    }
}
