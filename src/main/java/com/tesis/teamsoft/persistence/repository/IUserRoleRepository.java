package com.tesis.teamsoft.persistence.repository;

import com.tesis.teamsoft.persistence.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
}
