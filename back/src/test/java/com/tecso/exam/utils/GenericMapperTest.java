package com.tecso.exam.utils;

import com.tecso.exam.domain.JuridicPerson;
import com.tecso.exam.domain.dto.JuridicPersonDTO;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GenericMapperTest {

    GenericMapper<JuridicPerson, JuridicPersonDTO> mapper = new GenericMapper(JuridicPerson.class, JuridicPersonDTO.class);

    @Test
    public void convertToDTO_shouldReturnConvertedEntity() {
        JuridicPerson juridicPerson = new JuridicPerson("123", "Tecso", 2021);
        JuridicPersonDTO juridicPersonDTO = mapper.convertToDTO(juridicPerson);
        assertInstancesAreEqual(juridicPerson, juridicPersonDTO);
    }

    @Test
    public void convertToEntity_shouldConvertDTOtoEntity() {
        JuridicPersonDTO dto = new JuridicPersonDTO("rut", "name", 2021);
        JuridicPerson juridicPerson = mapper.convertToEntity(dto);
        assertInstancesAreEqual(juridicPerson, dto);
    }

    @Test
    public void convertToDTOList_shouldReturnMappedList() {
        JuridicPerson juridicPerson = new JuridicPerson("1233", "Tecso", 2021);
        JuridicPerson juridicPerson2 = new JuridicPerson("1234", "Tecso", 2021);
        JuridicPerson juridicPerson3 = new JuridicPerson("1235", "Tecso", 2021);
        List<JuridicPerson> juridicPeople = Arrays.asList(juridicPerson, juridicPerson2, juridicPerson3);
        List<JuridicPersonDTO> juridicPersonDTOS = mapper.convertToDTO(juridicPeople);
        assertEquals(juridicPeople.size(), 3);
        for(int i = 0; i < juridicPersonDTOS.size(); i++) {
            assertInstancesAreEqual(juridicPeople.get(i), juridicPersonDTOS.get(i));
        }
    }

    private void assertInstancesAreEqual(JuridicPerson juridicPerson, JuridicPersonDTO juridicPersonDTO) {
        assertEquals(juridicPersonDTO.getRut(), juridicPerson.getRut());
        assertEquals(juridicPersonDTO.getName(), juridicPerson.getName());
        assertEquals(juridicPersonDTO.getFoundationYear(), juridicPerson.getFoundationYear());
    }
}
