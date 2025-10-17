package com.tesis.teamsoft.service.implementation;

import com.tesis.teamsoft.persistence.entity.ReligionEntity;
import com.tesis.teamsoft.persistence.repository.IReligionRepository;
import com.tesis.teamsoft.presentation.dto.ReligionDTO;
import com.tesis.teamsoft.service.interfaces.IReligionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReligionServiceImpl implements IReligionService {

    @Autowired
    IReligionRepository religionRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public ReligionDTO.ReligionResponseDTO saveReligion(ReligionDTO.ReligionCreateDTO religionDTO) {

        try {
            ReligionEntity savedReligion = modelMapper.map(religionDTO, ReligionEntity.class);
            religionRepository.save(savedReligion);
            return modelMapper.map(savedReligion, ReligionDTO.ReligionResponseDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Error saving religion: " + e.getMessage());
        }


    }

    @Override
    public ReligionDTO.ReligionResponseDTO updateReligion(ReligionDTO.ReligionCreateDTO religionDTO, Long id) {

        if (!religionRepository.existsById(id)) {
            throw new RuntimeException("Religion not found");
        }

        ReligionEntity savedReligion = religionRepository.save(modelMapper.map(religionDTO, ReligionEntity.class));
        return modelMapper.map(savedReligion, ReligionDTO.ReligionResponseDTO.class);
    }

    @Override
    public String deleteReligion(Long id) {

        if (!religionRepository.existsById(id)) {
            throw new RuntimeException("Religion not found");
        }
        religionRepository.deleteById(id);
        return "Religion deleted";
    }

    @Override
    public List<ReligionDTO.ReligionResponseDTO> findAllReligion() {
        return religionRepository.findAll()
                .stream()
                .map(religionEntity -> modelMapper.map(religionEntity, ReligionDTO.ReligionResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReligionDTO.ReligionResponseDTO> findAllByOrderByIdAsc() {
        return religionRepository.findAllByOrderByIdAsc()
                .stream()
                .map(religionEntity -> modelMapper.map(religionEntity, ReligionDTO.ReligionResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReligionDTO.ReligionResponseDTO findReligionById(Long id) {

        if (!religionRepository.existsById(id)) {
            throw new RuntimeException("Religion not found");
        }

        return modelMapper.map(religionRepository.findById(id), ReligionDTO.ReligionResponseDTO.class);
    }
}
