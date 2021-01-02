package com.tecso.exam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecso.exam.config.ControllerIntegrationTestConfig;
import com.tecso.exam.domain.JuridicPerson;
import com.tecso.exam.domain.dto.JuridicPersonDTO;
import com.tecso.exam.service.JuridicPersonService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class JuridicPersonControllerIntegrationTest extends ControllerIntegrationTestConfig {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JuridicPersonService juridicPersonService;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;
    String url;

    @BeforeAll
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.url = this.localUrl.concat("/juridic-person");
    }

    @Test
    void findAll_shouldReturnListOfPersons() throws Exception {
        JuridicPerson juridicPerson = new JuridicPerson("RUT", "Tecso", 2021);
        JuridicPerson juridicPerson2 = new JuridicPerson("r2", "Tecso2", 2021);
        this.juridicPersonService.save(juridicPerson);
        this.juridicPersonService.save(juridicPerson2);

        MvcResult mvcResult = mockMvc.perform(get(this.url)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<JuridicPerson> juridicPeople = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);
        assertNotNull(juridicPeople);
        assertEquals(juridicPeople.size(),2);
    }

    @Test
    @Transactional
    void postJuridicPerson_shouldReturnSavedPerson() throws Exception {
        JuridicPersonDTO juridicPersonDTO = new JuridicPersonDTO("R1", "Fund", 2021);
        String dtoJson = objectMapper.writeValueAsString(juridicPersonDTO);

        MvcResult mvcResult = mockMvc.perform(post(this.url)
                .content(dtoJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        JuridicPersonDTO expenseResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), JuridicPersonDTO.class);
        assertNotNull(expenseResult);
    }

    @Test
    @Transactional
    void updateJuridicPerson_shouldReturnUpdatedPerson() throws Exception {
        JuridicPerson juridicPerson = new JuridicPerson("RUT", "Tecso", 2021);
        this.juridicPersonService.save(juridicPerson);
        JuridicPersonDTO juridicPersonDTO = new JuridicPersonDTO("RUT", "Fund", 2021);
        String dtoJson = objectMapper.writeValueAsString(juridicPersonDTO);

        MvcResult mvcResult = mockMvc.perform(put(this.url + "/RUT")
                .content(dtoJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        JuridicPersonDTO expenseResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), JuridicPersonDTO.class);
        assertNotNull(expenseResult);
    }

    @Test
    void getByRut_shouldReturnValue() throws Exception {
        JuridicPerson juridicPerson = new JuridicPerson("RUT", "Tecso", 2021);
        this.juridicPersonService.save(juridicPerson);

        MvcResult mvcResult = mockMvc.perform(get(this.url + "/RUT")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        JuridicPersonDTO expenseResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), JuridicPersonDTO.class);
        assertNotNull(expenseResult);
    }

    @Test
    void deleteById_shouldRemoveRecord() throws Exception {
        JuridicPerson juridicPerson = new JuridicPerson("RUT", "Tecso", 2021);
        this.juridicPersonService.save(juridicPerson);

        mockMvc.perform(delete(this.url + "/RUT")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        boolean existsById = juridicPersonService.existsById("RUT");
        assertFalse(existsById);
    }
}
