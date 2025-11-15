package com.tesis.teamsoft.persistence.repository;

import com.tesis.teamsoft.persistence.entity.PersonConflictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPersonConflictRepository extends JpaRepository<PersonConflictEntity, Long> {
    List<PersonConflictEntity> findByPersonId(Long personId);
    Optional<PersonConflictEntity> findByPersonIdAndPersonConflictIdAndIndexId(Long personId, Long personConflictId, Long indexId);
}