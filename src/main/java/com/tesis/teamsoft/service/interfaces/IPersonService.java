package com.tesis.teamsoft.service.interfaces;

import com.tesis.teamsoft.presentation.dto.PersonDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonService {
    PersonDTO.PersonResponseDTO savePerson(PersonDTO.PersonCreateDTO personDTO);

    PersonDTO.PersonResponseDTO updatePerson(PersonDTO.PersonCreateDTO personDTO, Long id);

    String deletePerson(Long id);

    List<PersonDTO.PersonResponseDTO> findAllPerson();

    List<PersonDTO.PersonResponseDTO> findAllByOrderByIdAsc();

    PersonDTO.PersonResponseDTO findPersonById(Long id);
}