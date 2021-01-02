package com.tecso.exam.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JuridicPersonDTO {
    private String rut;
    private String name;
    private Integer foundationYear;
}
