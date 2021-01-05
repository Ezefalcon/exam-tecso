package com.tecso.exam.repository;

import com.tecso.exam.domain.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findAllByAccountIdOrderByDateDesc(Long id);
}
