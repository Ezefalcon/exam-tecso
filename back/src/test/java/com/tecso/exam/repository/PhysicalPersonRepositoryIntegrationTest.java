package com.tecso.exam.repository;

import com.tecso.exam.domain.JuridicPerson;
import com.tecso.exam.domain.PhysicalPerson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
public class PhysicalPersonRepositoryIntegrationTest extends RepositoryConfigurationTest {

    @Autowired
    private PhysicalPersonRepository physicalPersonRepository;

    @Autowired
    private JuridicPersonRepository juridicPersonRepository;

    @Test
    public void onSave_shouldBeFound() {
        PhysicalPerson physicalPerson = new PhysicalPerson("123", "Juan", "T", "CC");
        PhysicalPerson persist = entityManager.persist(physicalPerson);
        entityManager.flush();

        Optional<PhysicalPerson> physicalPersonFound = physicalPersonRepository.findById(persist.getId());

        assertTrue(physicalPersonFound.isPresent());
        String name = physicalPersonFound.get().getName();
        assertEquals("Juan", name);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void saveTwoPersonsWithSameRut_shouldThrowException() {
        PhysicalPerson physicalPerson = new PhysicalPerson("123", "Juan", "T", "CC");
        physicalPersonRepository.save(physicalPerson);
        JuridicPerson asd = new JuridicPerson("123", "asd", 2020);
        juridicPersonRepository.save(asd);
    }
}
