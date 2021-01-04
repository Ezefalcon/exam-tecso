package com.tecso.exam.service;

import com.tecso.exam.domain.PhysicalPerson;
import com.tecso.exam.repository.PhysicalPersonRepository;
import com.tecso.exam.service.exceptions.alreadyexists.PhysicalPersonAlreadyExistsException;
import com.tecso.exam.service.exceptions.notfound.PhysicalPersonNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PhysicalPersonServiceImpl implements PhysicalPersonService {

    private PhysicalPersonRepository physicalPersonRepository;

    @Override
    public PhysicalPerson save(PhysicalPerson physicalPerson) {
        if(!physicalPersonRepository.existsByRut(physicalPerson.getRut())) {
            return physicalPersonRepository.save(physicalPerson);
        }
        throw new PhysicalPersonAlreadyExistsException();
    }

    @Override
    public PhysicalPerson update(PhysicalPerson physicalPerson) {
        if(existsById(physicalPerson.getId())) {
            return physicalPersonRepository.save(physicalPerson);
        }
        throw new PhysicalPersonNotFoundException();
    }

    @Override
    public PhysicalPerson findById(Long id) {
        Optional<PhysicalPerson> physicalPerson = physicalPersonRepository.findById(id);
        if(physicalPerson.isPresent()) {
            return physicalPerson.get();
        }
        throw new PhysicalPersonNotFoundException();
    }

    @Override
    public List<PhysicalPerson> findAll() {
        return physicalPersonRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        physicalPersonRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return physicalPersonRepository.existsById(id);
    }
}
