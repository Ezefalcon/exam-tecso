package com.tecso.exam.domain;


import lombok.Getter;

import java.math.BigDecimal;

public enum Currency {
    USD(BigDecimal.valueOf(300)),
    ARS(BigDecimal.valueOf(1000)),
    EUR(BigDecimal.valueOf(150));

    @Getter
    private BigDecimal maxValue;

    Currency(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }
}
