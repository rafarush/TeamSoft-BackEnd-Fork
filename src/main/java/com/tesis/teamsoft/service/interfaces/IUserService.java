package com.tesis.teamsoft.service.interfaces;

import com.tesis.teamsoft.presentation.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserService {

    UserDTO.UserResponseDTO saveUser(UserDTO.UserCreateDTO userDTO);

    UserDTO.UserResponseDTO updateUser(UserDTO.UserCreateDTO userDTO, Long id);

    String deleteUser(Long id);

    List<UserDTO.UserResponseDTO> findAllUsers();

    List<UserDTO.UserResponseDTO> findAllByOrderByIdAsc();

    UserDTO.UserResponseDTO findUserById(Long id);

    UserDTO.UserResponseDTO findByMail(String email);
}