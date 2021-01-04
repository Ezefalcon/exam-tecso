package com.tecso.exam.service;

import com.tecso.exam.domain.JuridicPerson;
import com.tecso.exam.repository.JuridicPersonRepository;
import com.tecso.exam.service.exceptions.alreadyexists.JuridicPersonAlreadyExistsException;
import com.tecso.exam.service.exceptions.notfound.JuridicPersonNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class JuridicPersonServiceTest {

    @Mock
    JuridicPersonRepository juridicPersonRepository;

    @InjectMocks
    JuridicPersonService juridicPersonService  = new JuridicPersonServiceImpl(juridicPersonRepository);


    @Test(expected = JuridicPersonAlreadyExistsException.class)
    public void save_shouldThrowAlreadyExistsException() {
        JuridicPerson juridicPerson = new JuridicPerson("123", "asd", 2021);

        when(juridicPersonRepository.existsByRut("123")).thenReturn(true);
        when(juridicPersonService.save(juridicPerson)).thenReturn(juridicPerson);

        juridicPersonService.save(juridicPerson);
    }

    @Test(expected = JuridicPersonNotFoundException.class)
    public void findById_shouldThrowNotFoundException() {
        when(juridicPersonRepository.findById(1l)).thenReturn(Optional.empty());

        juridicPersonService.findById(1l);
    }
}
