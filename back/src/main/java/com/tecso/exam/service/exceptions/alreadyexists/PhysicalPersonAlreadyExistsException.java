package com.tecso.exam.service.exceptions.alreadyexists;

import com.tecso.exam.service.exceptions.alreadyexists.AlreadyExistsException;

public class PhysicalPersonAlreadyExistsException extends AlreadyExistsException {
    public PhysicalPersonAlreadyExistsException() {
        super("Phyisical person");
    }
}
