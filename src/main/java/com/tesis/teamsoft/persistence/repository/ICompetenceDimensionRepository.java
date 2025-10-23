package com.tesis.teamsoft.persistence.repository;

import com.tesis.teamsoft.persistence.entity.CompetenceDimensionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICompetenceDimensionRepository extends JpaRepository<CompetenceDimensionEntity, Long> {

    @Query("SELECT cd FROM CompetenceDimensionEntity cd WHERE cd.competence.id = :competenceId")
    List<CompetenceDimensionEntity> findByCompetenceId(@Param("competenceId") Long competenceId);
}
