package com.tecso.exam.service;

import com.tecso.exam.domain.JuridicPerson;
import com.tecso.exam.repository.JuridicPersonRepository;
import com.tecso.exam.service.exceptions.JuridicPersonAlreadyExistsException;
import com.tecso.exam.service.exceptions.JuridicPersonNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JuridicPersonServiceImpl implements JuridicPersonService {

    private JuridicPersonRepository juridicPersonRepository;

    public JuridicPersonServiceImpl(JuridicPersonRepository juridicPersonRepository) {
        this.juridicPersonRepository = juridicPersonRepository;
    }

    @Override
    public JuridicPerson save(JuridicPerson juridicPerson) {
        if(!juridicPersonRepository.existsById(juridicPerson.getRut())) {
            return juridicPersonRepository.save(juridicPerson);
        }
        throw new JuridicPersonAlreadyExistsException();
    }

    @Override
    public JuridicPerson update(JuridicPerson juridicPerson) {
        if(existsById(juridicPerson.getRut())) {
            return juridicPersonRepository.save(juridicPerson);
        }
        throw new JuridicPersonNotFoundException();
    }

    @Override
    public JuridicPerson findById(String id) {
        Optional<JuridicPerson> juridicPerson = juridicPersonRepository.findById(id);
        if(juridicPerson.isPresent()) {
            return juridicPerson.get();
        }
        throw new JuridicPersonNotFoundException();
    }

    @Override
    public List<JuridicPerson> findAll() {
        return juridicPersonRepository.findAll();
    }

    @Override
    public void deleteById(String rut) {
        juridicPersonRepository.deleteById(rut);
    }

    @Override
    public boolean existsById(String id) {
        return juridicPersonRepository.existsById(id);
    }
}
