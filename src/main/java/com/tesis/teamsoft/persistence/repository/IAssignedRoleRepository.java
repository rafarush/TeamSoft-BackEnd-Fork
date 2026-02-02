package com.tesis.teamsoft.persistence.repository;

import com.tesis.teamsoft.persistence.entity.AssignedRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAssignedRoleRepository extends JpaRepository<AssignedRoleEntity, Long> {
}
