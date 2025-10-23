package com.tesis.teamsoft.service.interfaces;

import com.tesis.teamsoft.presentation.dto.RoleDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleService {

    RoleDTO.RoleResponseDTO saveRole(RoleDTO.RoleCreateDTO roleDTO);

    RoleDTO.RoleResponseDTO updateRole(RoleDTO.RoleCreateDTO roleDTO, Long id);

    String deleteRole(Long id);

    List<RoleDTO.RoleResponseDTO> findAllRole();

    List<RoleDTO.RoleResponseDTO> findAllByOrderByIdAsc();

    RoleDTO.RoleResponseDTO findRoleById(Long id);
}