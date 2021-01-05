package com.tecso.exam.domain.dto;

import com.tecso.exam.domain.Currency;
import com.tecso.exam.domain.Movement;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class AccountDTO {
    private Long id;
    private Long accountNumber;
    private List<MovementDTO> movements;
    private Currency currency;
    private BigDecimal balance;
}
