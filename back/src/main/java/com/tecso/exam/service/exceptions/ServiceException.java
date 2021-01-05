package com.tecso.exam.service.exceptions;

public class ServiceException extends IllegalArgumentException {
    public ServiceException(String message) {
        super(message);
    }
}
