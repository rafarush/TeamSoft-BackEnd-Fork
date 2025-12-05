package com.tesis.teamsoft.persistence.repository;

import com.tesis.teamsoft.persistence.entity.ConflictIndexEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IConflictIndexRepository extends JpaRepository<ConflictIndexEntity, Long> {

    List<ConflictIndexEntity> findAllByOrderByIdAsc();

    ConflictIndexEntity findFirstByOrderByWeightDesc();
}