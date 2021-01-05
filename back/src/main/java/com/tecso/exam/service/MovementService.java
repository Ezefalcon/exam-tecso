package com.tecso.exam.service;

import com.tecso.exam.domain.Movement;

import java.util.List;

public interface MovementService {
    Movement save(Movement movement);
    void deleteById(Long id);
    List<Movement> findAll();
    List<Movement> findAll(Long accountId);
}
