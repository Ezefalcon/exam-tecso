package com.tecso.exam.repository;


import com.tecso.exam.domain.JuridicPerson;
import com.tecso.exam.domain.PhysicalPerson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
public class JuridicPersonRepositoryIntegrationTest extends RepositoryConfigurationTest {

    @Autowired
    JuridicPersonRepository juridicPersonRepository;

    @Test
    public void onSave_shouldBeFound() {
        JuridicPerson juridicPerson = new JuridicPerson("123", "Tecso", 2021);
        JuridicPerson saved = entityManager.persist(juridicPerson);
        entityManager.flush();

        Optional<JuridicPerson> physicalPersonFound = juridicPersonRepository.findById(saved.getId());

        assertTrue(physicalPersonFound.isPresent());
        String name = physicalPersonFound.get().getName();
        assertEquals("Tecso", name);
    }
}