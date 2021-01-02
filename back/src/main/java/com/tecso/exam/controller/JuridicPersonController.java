package com.tecso.exam.controller;

import com.tecso.exam.domain.JuridicPerson;
import com.tecso.exam.domain.dto.JuridicPersonDTO;
import com.tecso.exam.service.JuridicPersonService;
import com.tecso.exam.utils.GenericMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/juridic-person")
public class JuridicPersonController {

    JuridicPersonService juridicPersonService;

    GenericMapper<JuridicPerson, JuridicPersonDTO> mapper;

    public JuridicPersonController(JuridicPersonService juridicPersonRepository) {
        this.juridicPersonService = juridicPersonRepository;
        mapper = new GenericMapper<>(JuridicPerson.class, JuridicPersonDTO.class);
    }

    @GetMapping
    public List<JuridicPersonDTO> findAll() {
        List<JuridicPerson> all = this.juridicPersonService.findAll();
        return mapper.convertToDTO(all);
    }

    @GetMapping("/{id}")
    public JuridicPersonDTO getJuridicPersonByRut(@PathVariable String id) {
        JuridicPerson byId = this.juridicPersonService.findById(id);
        return mapper.convertToDTO(byId);
    }

    @PostMapping
    public JuridicPersonDTO save(@RequestBody JuridicPersonDTO juridicPersonDTO) {
        JuridicPerson saved = juridicPersonService.save(mapper.convertToEntity(juridicPersonDTO));
        return mapper.convertToDTO(saved);
    }

    @PutMapping("/{id}")
    public JuridicPersonDTO update(@RequestBody JuridicPersonDTO juridicPersonDTO, @PathVariable String id) {
        JuridicPerson juridicPerson = mapper.convertToEntity(juridicPersonDTO);
        juridicPerson.setRut(id);
        JuridicPerson saved = juridicPersonService.update(juridicPerson);
        return mapper.convertToDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        juridicPersonService.deleteById(id);
    }

}
