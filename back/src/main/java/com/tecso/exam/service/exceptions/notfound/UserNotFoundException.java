package com.tecso.exam.service.exceptions.notfound;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("User");
    }
}
