package com.tecso.exam.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Person {
    @Id
    protected String rut;
    protected String name;
}
