package com.tesis.teamsoft.service.interfaces;

import com.tesis.teamsoft.presentation.dto.AgeGroupDTO;
import com.tesis.teamsoft.presentation.dto.ReligionDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAgeGroupService {

    AgeGroupDTO.AgeGroupResponseDTO saveAgeGroup(AgeGroupDTO.AgeGroupCreateDTO ageGroupDTO);

    AgeGroupDTO.AgeGroupResponseDTO updateAgeGroup(AgeGroupDTO.AgeGroupCreateDTO ageGroupDTO, Long id);

    String deleteAgeGroup(Long id);

    List<AgeGroupDTO.AgeGroupResponseDTO> findAllAgeGroup();

    List<AgeGroupDTO.AgeGroupResponseDTO> findAllByOrderByIdAsc();

    AgeGroupDTO.AgeGroupResponseDTO findAgeGroupById(Long id);

}
