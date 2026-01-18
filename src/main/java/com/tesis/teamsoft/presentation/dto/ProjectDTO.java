package com.tesis.teamsoft.presentation.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProjectDTO {

    @Data
    public static class ProjectResponseDTO {
        private long id;
        private String projectName;
    }

    @Getter
    @Setter
    public static class ProjectTeamProposalDTO {
        private ProjectDTO.ProjectResponseDTO project;
        List<AssignedRoleDTO> assignedRoles;

        public ProjectTeamProposalDTO(ProjectDTO.ProjectResponseDTO project) {
            this.project = project;
            assignedRoles = new ArrayList<>();
        }
    }
}
