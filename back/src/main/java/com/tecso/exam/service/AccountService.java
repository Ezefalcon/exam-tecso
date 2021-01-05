package com.tecso.exam.service;

import com.tecso.exam.domain.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account save(Account t);
    Account findById(Long id);
    List<Account> findAll();
    void deleteById(Long id);
    BigDecimal updateAccountBalance(Long accountId, BigDecimal amount);
}
