package com.tecso.exam.repository;

import com.tecso.exam.domain.JuridicPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuridicPersonRepository extends JpaRepository<JuridicPerson, Long> {
    boolean existsByRut(String s);
}
