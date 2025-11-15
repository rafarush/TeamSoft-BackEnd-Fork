package com.tesis.teamsoft.persistence.repository;

import com.tesis.teamsoft.persistence.entity.PersonalProjectInterestsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPersonalProjectInterestsRepository extends JpaRepository<PersonalProjectInterestsEntity, Long> {
    List<PersonalProjectInterestsEntity> findByPersonId(Long personId);
    Optional<PersonalProjectInterestsEntity> findByPersonIdAndProjectId(Long personId, Long projectId);
}