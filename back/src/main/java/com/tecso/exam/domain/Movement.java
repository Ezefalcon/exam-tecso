package com.tecso.exam.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Movement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private LocalDateTime date;
    private MovementType movementType;
    private String description;
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public Movement(LocalDateTime date, MovementType movementType, String description, BigDecimal amount, Account account) {
        this.date = date;
        this.movementType = movementType;
        this.description = description;
        this.amount = amount;
        this.account = account;
    }
}
