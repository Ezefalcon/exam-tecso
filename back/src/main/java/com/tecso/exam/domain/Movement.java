package com.tecso.exam.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Movement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private LocalDate date;
    private MovementType movementType;
    private String description;
    private BigDecimal amount;
}
