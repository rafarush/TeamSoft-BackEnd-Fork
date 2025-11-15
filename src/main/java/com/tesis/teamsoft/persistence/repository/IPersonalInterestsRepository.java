package com.tesis.teamsoft.persistence.repository;

import com.tesis.teamsoft.persistence.entity.PersonalInterestsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPersonalInterestsRepository extends JpaRepository<PersonalInterestsEntity, Long> {
    List<PersonalInterestsEntity> findByPersonId(Long personId);
    Optional<PersonalInterestsEntity> findByPersonIdAndRoleId(Long personId, Long roleId);
}