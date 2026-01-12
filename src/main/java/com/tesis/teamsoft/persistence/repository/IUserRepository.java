package com.tesis.teamsoft.persistence.repository;

import com.tesis.teamsoft.persistence.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findAllByOrderByIdAsc();

    Optional<UserEntity> findByMail(@NotBlank(message = "Email is required") @Email String email);
}
