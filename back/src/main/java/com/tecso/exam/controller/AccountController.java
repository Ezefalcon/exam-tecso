package com.tecso.exam.controller;

import com.tecso.exam.domain.Account;
import com.tecso.exam.domain.Movement;
import com.tecso.exam.domain.dto.MovementDTO;
import com.tecso.exam.domain.dto.AccountDTO;
import com.tecso.exam.domain.dto.AccountRequest;
import com.tecso.exam.service.AccountService;
import com.tecso.exam.service.MovementService;
import com.tecso.exam.utils.GenericMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;
    private MovementService movementService;
    private GenericMapper<Account, AccountDTO> accountMapper;
    private GenericMapper<Movement, MovementDTO> movementMapper;

    public AccountController(AccountService accountService, MovementService movementService) {
        this.accountService = accountService;
        this.movementService = movementService;
        this.accountMapper = new GenericMapper<>(Account.class, AccountDTO.class);
        this.movementMapper = new GenericMapper<>(Movement.class, MovementDTO.class);
    }

    @GetMapping
    public List<AccountDTO> findAll() {
        List<Account> all = accountService.findAll();
        List<AccountDTO> accountDTOS = accountMapper.convertToDTO(all);
        return accountDTOS;
    }

    @PostMapping
    public AccountDTO save(@RequestBody AccountRequest account) {
        Account acc = new Account(account.getAccountNumber(), account.getCurrency(), account.getBalance());
        return accountMapper.convertToDTO(accountService.save(acc));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        accountService.deleteById(id);
    }

    @PostMapping("/{accountId}/movement")
    public MovementDTO addMovement(@PathVariable Long accountId, @RequestBody MovementDTO movementDTO) {
        Account byId = accountService.findById(accountId);
        Movement movement = movementMapper.convertToEntity(movementDTO);
        movement.setAccount(byId);
        return movementMapper.convertToDTO(movementService.save(movement));
    }

    @GetMapping("/{accountId}/movement")
    public List<MovementDTO> findAllByAccountId(@PathVariable Long accountId) {
        return movementMapper.convertToDTO(movementService.findAll(accountId));
    }

}
