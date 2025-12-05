package com.tesis.teamsoft.persistence.repository;

import com.tesis.teamsoft.persistence.entity.LevelsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILevelsRepository extends JpaRepository<LevelsEntity, Long> {

    List<LevelsEntity> findAllByOrderByIdAsc();

    LevelsEntity findFirstByOrderByLevelsDesc();

    LevelsEntity findFirstByOrderByLevelsAsc();
}