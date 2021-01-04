package com.tecso.exam.service.exceptions.alreadyexists;

/**
 * Created by efalcon
 */
public class UsernameAlreadyExistsException extends AlreadyExistsException {
    public UsernameAlreadyExistsException() {
        super("Username");
    }
}
