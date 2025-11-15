package com.tesis.teamsoft.pojos;

import com.tesis.teamsoft.persistence.entity.ProjectEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Plantilla para variar las competencias asociadas a un proyecto en la pantalla
 * de asignacion del jefe de proyecto
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ProjectRoleCompetenceTemplate implements Serializable{

    private ProjectEntity project;
    private List<ProjectCompetenceTemplate> roleCompetences;

    /**
     * Busca un proyecto por su id en una lista de
     * ProjectRoleCompompetenceTemplate
     *
     */
    public static ProjectRoleCompetenceTemplate findProjectById(List<ProjectRoleCompetenceTemplate> list, ProjectEntity project) {
        ProjectRoleCompetenceTemplate searchResult = null;
        int i = 0;
        boolean found = false;
        while (i < list.size() && !found) {
            if (project.getId().equals(list.get(i).getProject().getId())) {
                searchResult = list.get(i);
            }

            i++;
        }
        return searchResult;
    }
}
