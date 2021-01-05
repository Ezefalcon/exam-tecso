package com.tecso.exam.repository;

import com.tecso.exam.domain.Account;
import com.tecso.exam.domain.Currency;
import com.tecso.exam.domain.Movement;
import com.tecso.exam.domain.MovementType;
import com.tecso.exam.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertTrue;

class MovementRepositoryIntegrationTest extends RepositoryConfigurationTest {

    @Autowired
    MovementRepository movementRepository;

    @Autowired
    AccountRepository accountService;

    @Test
    void findAllByAccountId() {
        Account account = new Account(123L, Currency.ARS, BigDecimal.ZERO);
        accountService.save(account);
        Movement movement = new Movement(LocalDateTime.now(), MovementType.DEBIT, "", BigDecimal.valueOf(200).setScale(2), account);
        movementRepository.save(movement);
        List<Movement> allByAccountId = movementRepository.findAllByAccountIdOrderByDateDesc(1l);
        assertTrue(allByAccountId.size() > 0);
    }
}