package com.tecso.exam.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class JuridicPerson extends Person {
    private Integer foundationYear;

    public JuridicPerson(String rut, String name, Integer foundationYear) {
        this.rut = rut;
        this.name = name;
        this.foundationYear = foundationYear;
    }
}
