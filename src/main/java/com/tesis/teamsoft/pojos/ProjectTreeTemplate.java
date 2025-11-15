package com.tesis.teamsoft.pojos;

import com.tesis.teamsoft.persistence.entity.ProjectEntity;
import com.tesis.teamsoft.persistence.entity.ProjectRolesEntity;
import com.tesis.teamsoft.persistence.entity.ProjectStructureEntity;
import com.tesis.teamsoft.persistence.entity.ProjectTechCompetenceEntity;
import lombok.*;

import java.util.List;

@Deprecated
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ProjectTreeTemplate {

    private String displayName;

    private ProjectStructureEntity structure;
    private ProjectEntity project;
    private ProjectRolesEntity roles;
    private ProjectTechCompetenceEntity projectCompetences;

    private String structureName = "-";
    private String projectName = "-";
    private String rolesName = "-";
    private String projectCompetencesName = "-";

    //crei que haria falta pero aun no le encuentro utilidad
    private List<ProjectStructureEntity> structureList;
    private List<ProjectEntity> projectList;
    private List<ProjectRolesEntity> rolesList;
    private List<ProjectTechCompetenceEntity> projectCompetencesList;


    public void setStructureName(String structureName) {
        this.structureName = structureName;
        this.displayName = "Structure/Estructura";
    }
    public void setProjectName(String projectName) {
        
        this.projectName = projectName;
        this.displayName = "Project/Proyecto";
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
        this.displayName = "Role/Rol";
    }

    public void setProjectCompetencesName(String projectCompetencesName) {
        this.projectCompetencesName = projectCompetencesName;
        this.displayName = "Competence/Competencia";
    }
}
