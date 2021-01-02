package com.tecso.exam.service;

import com.tecso.exam.config.SimpleIntegrationTestConfiguration;
import com.tecso.exam.domain.JuridicPerson;
import com.tecso.exam.domain.PhysicalPerson;
import com.tecso.exam.repository.PhysicalPersonRepository;
import com.tecso.exam.service.exceptions.JuridicPersonNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
public class JuridicPersonIntegrationTest extends SimpleIntegrationTestConfiguration {

    @Autowired
    JuridicPersonService juridicPersonService;

    @Autowired
    PhysicalPersonRepository physicalPersonRepository;

    @BeforeAll
    public void init() {
        JuridicPerson juridicPerson = new JuridicPerson("123", "Tecso", 2021);
        juridicPersonService.save(juridicPerson);
        physicalPersonRepository.save(new PhysicalPerson("1233","asd","aas","as"));
    }

    @Test
    public void findAll_shouldRetrieve1Person() {
        List<JuridicPerson> all = juridicPersonService.findAll();
        assertEquals(all.size(), 1);
    }

    @Test
    public void save_shouldPersist() {
        JuridicPerson juridicPerson = new JuridicPerson("132341242", "Tecso", 2021);

        juridicPersonService.save(juridicPerson);

        JuridicPerson byId = juridicPersonService.findById("132341242");
        assertEquals(juridicPerson, byId);
    }

    @Test
    public void update_shouldUpdateRecord() {
        JuridicPerson juridicPerson = new JuridicPerson("123", "Tecso updated", 2021);

        JuridicPerson update = juridicPersonService.update(juridicPerson);

        JuridicPerson byId = juridicPersonService.findById("123");
        assertEquals("Tecso updated", byId.getName());
    }

    @Test
    public void findById_shouldReturnAJuridicPerson() {
        JuridicPerson byId = juridicPersonService.findById("123");
        assertNotNull(byId);
    }

    @Test
    public void findById_shouldThrowNotFoundException() {
        assertThrows(JuridicPersonNotFoundException.class,
                () -> juridicPersonService.findById("132341242222"));
    }

    @Test
    public void deleteById_shouldDeletePerson() {
        String rut = "132341242";
        JuridicPerson juridicPerson = new JuridicPerson(rut, "Tecso", 2021);
        juridicPersonService.save(juridicPerson);

        juridicPersonService.deleteById(rut);
        assertFalse(juridicPersonService.existsById(rut));
    }
}
