package com.tecso.exam.service;

import com.tecso.exam.domain.JuridicPerson;
import com.tecso.exam.repository.JuridicPersonRepository;
import com.tecso.exam.service.exceptions.JuridicPersonAlreadyExistsException;
import com.tecso.exam.service.exceptions.JuridicPersonNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
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

        when(juridicPersonRepository.existsById("123")).thenReturn(true);
        when(juridicPersonService.save(juridicPerson)).thenReturn(juridicPerson);

        juridicPersonService.save(juridicPerson);
    }

    @Test(expected = JuridicPersonNotFoundException.class)
    public void findById_shouldThrowNotFoundException() {
        when(juridicPersonRepository.findById("123")).thenReturn(Optional.empty());

        juridicPersonService.findById("123");
    }
}
