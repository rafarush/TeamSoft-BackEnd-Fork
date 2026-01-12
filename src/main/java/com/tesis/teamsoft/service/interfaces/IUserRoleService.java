package com.tesis.teamsoft.service.interfaces;

import com.tesis.teamsoft.presentation.dto.RoleDTO;

import java.util.List;

public interface IUserRoleService {

    RoleDTO.RoleResponseDTO saveRole(RoleDTO.RoleCreateDTO roleDTO);

    RoleDTO.RoleResponseDTO updateRole(RoleDTO.RoleCreateDTO roleDTO, Long id);

    List<RoleDTO.RoleResponseDTO> findAllRoles();

    RoleDTO.RoleResponseDTO findRoleById(Long id);

    List<RoleDTO.RoleResponseDTO> findAllByOrderByIdAsc();

    RoleDTO.RoleResponseDTO findByName(String username);

    String deleteRole(Long id);
}
