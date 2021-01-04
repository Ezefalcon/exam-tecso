package com.tecso.exam.controller;

import com.tecso.exam.domain.PhysicalPerson;
import com.tecso.exam.domain.dto.PhysicalPersonDTO;
import com.tecso.exam.service.PhysicalPersonService;
import com.tecso.exam.utils.GenericMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/physical-person")
public class PhysicalPersonController {

    private PhysicalPersonService physicalPersonService;
    private GenericMapper<PhysicalPerson, PhysicalPersonDTO> mapper;

    public PhysicalPersonController(PhysicalPersonService juridicPersonRepository) {
        this.physicalPersonService = juridicPersonRepository;
        mapper = new GenericMapper<>(PhysicalPerson.class, PhysicalPersonDTO.class);
    }

    @GetMapping
    public List<PhysicalPersonDTO> findAll() {
        List<PhysicalPerson> all = this.physicalPersonService.findAll();
        return mapper.convertToDTO(all);
    }

    @GetMapping("/{id}")
    public PhysicalPersonDTO getJuridicPersonByRut(@PathVariable Long id) {
        PhysicalPerson byId = this.physicalPersonService.findById(id);
        return mapper.convertToDTO(byId);
    }

    @PostMapping
    public PhysicalPersonDTO save(@RequestBody PhysicalPersonDTO juridicPersonDTO) {
        PhysicalPerson saved = physicalPersonService.save(mapper.convertToEntity(juridicPersonDTO));
        return mapper.convertToDTO(saved);
    }

    @PutMapping("/{id}")
    public PhysicalPersonDTO update(@RequestBody PhysicalPersonDTO juridicPersonDTO, @PathVariable Long id) {
        PhysicalPerson juridicPerson = mapper.convertToEntity(juridicPersonDTO);
        juridicPerson.setId(id);
        PhysicalPerson saved = physicalPersonService.update(juridicPerson);
        return mapper.convertToDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        physicalPersonService.deleteById(id);
    }
}
