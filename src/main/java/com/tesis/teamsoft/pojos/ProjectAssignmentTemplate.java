package com.tesis.teamsoft.pojos;

import com.tesis.teamsoft.persistence.entity.ProjectEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectAssignmentTemplate {

    private ProjectEntity project;
    List<RolAssignedTemplate> assignedRoles;
}
