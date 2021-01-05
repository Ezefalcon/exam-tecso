package com.tecso.exam.service;

import com.tecso.exam.domain.Movement;
import com.tecso.exam.domain.MovementType;
import com.tecso.exam.repository.MovementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MovementServiceImpl implements MovementService {

    private MovementRepository movementRepository;
    private AccountService accountService;

    @Override
    public Movement save(Movement movement) {
        if(movement.getMovementType() == MovementType.DEBIT) {
            accountService.updateAccountBalance(movement.getAccount().getId(), movement.getAmount());
        }
        return movementRepository.save(movement);
    }

    @Override
    public void deleteById(Long id) {
        movementRepository.deleteById(id);
    }

    @Override
    public List<Movement> findAll() {
        return movementRepository.findAll();
    }

    @Override
    public List<Movement> findAll(Long accountId) {
        return movementRepository.findAllByAccountIdOrderByDateDesc(accountId);
    }
}
