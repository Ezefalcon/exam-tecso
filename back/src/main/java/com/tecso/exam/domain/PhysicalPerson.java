package com.tecso.exam.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
public class PhysicalPerson extends Person {
    private String lastName;
    private String cc;

    public PhysicalPerson(String rut, String name, String lastName, String cc) {
        this.rut = rut;
        this.name = name;
        this.lastName = lastName;
        this.cc = cc;
    }
}
