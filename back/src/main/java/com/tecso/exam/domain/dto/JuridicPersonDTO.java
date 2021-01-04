package com.tecso.exam.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JuridicPersonDTO {
    private Long id;
    private String rut;
    private String name;
    private Integer foundationYear;

    public JuridicPersonDTO(String rut, String name, Integer foundationYear) {
        this.rut = rut;
        this.name = name;
        this.foundationYear = foundationYear;
    }
}
