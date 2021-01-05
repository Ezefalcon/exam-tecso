package com.tecso.exam.domain.dto;

import com.tecso.exam.domain.Currency;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountRequest {
    private Long accountNumber;
    private Currency currency;
    private BigDecimal balance;
}
