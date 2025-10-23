package com.tesis.teamsoft.persistence.repository;

import com.tesis.teamsoft.persistence.entity.RoleCompetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRoleCompetitionRepository extends JpaRepository<RoleCompetitionEntity, Long> {

    List<RoleCompetitionEntity> findByRoleId(Long roleId);

    @Query("SELECT rc FROM RoleCompetitionEntity rc WHERE rc.role.id = :roleId AND rc.competence.id = :competenceId")
    Optional<RoleCompetitionEntity> findByRoleIdAndCompetenceId(@Param("roleId") Long roleId, @Param("competenceId") Long competenceId);

    @Modifying
    @Query("DELETE FROM RoleCompetitionEntity rc WHERE rc.role.id = :roleId AND rc.competence.id NOT IN :competenceIds")
    void deleteByRoleIdAndCompetenceIdNotIn(@Param("roleId") Long roleId, @Param("competenceIds") List<Long> competenceIds);

    @Modifying
    @Query("DELETE FROM RoleCompetitionEntity rc WHERE rc.role.id = :roleId")
    void deleteByRoleId(@Param("roleId") Long roleId);
}