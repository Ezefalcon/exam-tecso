package com.tecso.exam.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhysicalPersonDTO {
    private Long id;
    private String rut;
    private String name;
    private String lastName;
    private String cc;

    public PhysicalPersonDTO(String name, String lastName, String cc) {
        this.name = name;
        this.lastName = lastName;
        this.cc = cc;
    }
}
