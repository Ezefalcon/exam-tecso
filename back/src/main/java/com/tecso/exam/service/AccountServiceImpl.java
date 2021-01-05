package com.tecso.exam.service;

import com.tecso.exam.domain.Account;
import com.tecso.exam.repository.AccountRepository;
import com.tecso.exam.service.exceptions.ServiceException;
import com.tecso.exam.service.exceptions.notfound.AccountNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Override
    public Account save(Account account) {
        if(!accountRepository.existsByAccountNumber(account.getAccountNumber())) {
            return accountRepository.save(account);
        }
        throw new ServiceException("Account number already exists");
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        Account acc = this.findById(id);
        if(acc.isMovementsEmpty()) {
            accountRepository.deleteById(id);
        } else throw new ServiceException("Movements are not empty");
    }

    @Override
    public BigDecimal updateAccountBalance(Long accountId, BigDecimal amount) {
        Account byId = findById(accountId);
        byId.setBalance(byId.getBalance().subtract(amount).setScale(2, RoundingMode.HALF_UP));
        accountRepository.save(byId);
        return byId.getBalance();
    }

}
