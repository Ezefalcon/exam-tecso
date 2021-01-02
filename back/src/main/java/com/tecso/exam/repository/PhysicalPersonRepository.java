package com.tecso.exam.repository;

import com.tecso.exam.domain.PhysicalPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalPersonRepository extends JpaRepository<PhysicalPerson, String> {
}
