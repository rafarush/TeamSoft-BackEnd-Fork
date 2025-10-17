package com.tesis.teamsoft.service.interfaces;

import com.tesis.teamsoft.presentation.dto.ReligionDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReligionService {

    ReligionDTO.ReligionResponseDTO saveReligion(ReligionDTO.ReligionCreateDTO religionDTO);

    ReligionDTO.ReligionResponseDTO updateReligion(ReligionDTO.ReligionCreateDTO religionDTO, Long id);

    String deleteReligion(Long id);

    List<ReligionDTO.ReligionResponseDTO> findAllReligion();

    List<ReligionDTO.ReligionResponseDTO> findAllByOrderByIdAsc();

    ReligionDTO.ReligionResponseDTO findReligionById(Long id);
}
