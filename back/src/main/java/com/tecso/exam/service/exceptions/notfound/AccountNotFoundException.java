package com.tecso.exam.service.exceptions.notfound;

public class AccountNotFoundException extends NotFoundException {
    public AccountNotFoundException() {
        super("Account");
    }
}
