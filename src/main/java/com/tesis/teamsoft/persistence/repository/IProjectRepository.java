package com.tesis.teamsoft.persistence.repository;

import com.tesis.teamsoft.persistence.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectRepository extends JpaRepository<ProjectEntity, Long> {


}
