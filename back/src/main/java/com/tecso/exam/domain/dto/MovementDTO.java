package com.tecso.exam.domain.dto;

import com.tecso.exam.domain.MovementType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MovementDTO {
    private LocalDateTime date;
    private MovementType movementType;
    private String description;
    private BigDecimal amount;
}
