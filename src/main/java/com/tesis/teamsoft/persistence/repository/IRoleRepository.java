package com.tesis.teamsoft.persistence.repository;

import com.tesis.teamsoft.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    List<RoleEntity> findAllByOrderByIdAsc();
}