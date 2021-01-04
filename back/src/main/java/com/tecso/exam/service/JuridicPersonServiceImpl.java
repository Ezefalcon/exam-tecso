package com.tecso.exam.service;

import com.tecso.exam.domain.JuridicPerson;
import com.tecso.exam.repository.JuridicPersonRepository;
import com.tecso.exam.service.exceptions.alreadyexists.JuridicPersonAlreadyExistsException;
import com.tecso.exam.service.exceptions.alreadyexists.PersonRutAlreadyExistsException;
import com.tecso.exam.service.exceptions.notfound.JuridicPersonNotFoundException;
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
        if(!juridicPersonRepository.existsByRut(juridicPerson.getRut())) {
            return juridicPersonRepository.save(juridicPerson);
        }
        throw new JuridicPersonAlreadyExistsException();
    }

    @Override
    public JuridicPerson update(JuridicPerson juridicPerson) {
        Optional<JuridicPerson> foundOpt = juridicPersonRepository.findById(juridicPerson.getId());
        if(foundOpt.isPresent()) {
            JuridicPerson persistedPerson = foundOpt.get();
            if(!persistedPerson.getRut().equals(juridicPerson.getRut()) && juridicPersonRepository.existsByRut(juridicPerson.getRut())) {
                throw new PersonRutAlreadyExistsException();
            }
            return juridicPersonRepository.save(juridicPerson);
        }
        throw new JuridicPersonNotFoundException();
    }

    @Override
    public JuridicPerson findById(Long id) {
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
    public void deleteById(Long id) {
        juridicPersonRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return juridicPersonRepository.existsById(id);
    }
}
