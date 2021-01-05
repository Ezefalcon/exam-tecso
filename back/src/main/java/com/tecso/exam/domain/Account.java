package com.tecso.exam.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Getter @Setter
    private Long id;

    @Column(unique = true) @Getter @Setter
    private Long accountNumber;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
    private List<Movement> movements;

    @Getter @Setter
    private Currency currency;

    @Digits(integer = 2, fraction = 2) @Getter @Setter
    private BigDecimal balance;

    public Account(Long accountNumber, Currency currency, @Digits(integer = 2, fraction = 2) BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.balance = balance;
        this.movements = new ArrayList<>();
    }

    public boolean isMovementsEmpty() {
        return this.movements.isEmpty();
    }

    public void addMovement(Movement movement) {
        this.movements.add(movement);
    }

    public List<Movement> getMovements() {
        return new ArrayList<>(this.movements);
    }
}
