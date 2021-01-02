package com.tecso.exam.service.exceptions;

public class JuridicPersonAlreadyExistsException extends IllegalArgumentException {
    public JuridicPersonAlreadyExistsException() {
        super("Juridic person");
    }
}
