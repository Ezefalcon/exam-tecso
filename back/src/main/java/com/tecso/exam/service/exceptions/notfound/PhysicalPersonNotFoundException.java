package com.tecso.exam.service.exceptions.notfound;

public class PhysicalPersonNotFoundException extends NotFoundException {
    public PhysicalPersonNotFoundException() {
        super("Physical person");
    }
}
