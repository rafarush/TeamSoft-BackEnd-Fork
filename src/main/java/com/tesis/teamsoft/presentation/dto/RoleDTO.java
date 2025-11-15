package com.tesis.teamsoft.presentation.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class RoleDTO {

    @Data
    public static class RoleCreateDTO {
        @NotBlank(message = "Role name is required")
        @Size(min = 1, max = 1024)
        private String roleName;

        @NotBlank(message = "Role description is required")
        @Size(min = 1, max = 1024)
        private String roleDesc;

        @NotNull(message = "Impact is required")
        private Float impact;

        @NotNull(message = "Is boss is required")
        private Boolean isBoss;

        @Valid
        private List<RoleCompetitionCreateDTO> roleCompetitions;
        private List<Long> incompatibleRoleIds;
    }

    @Data
    public static class RoleResponseDTO {
        private Long id;
        private String roleName;
        private String roleDesc;
        private Float impact;
        private Boolean isBoss;
        private List<RoleCompetitionResponseDTO> roleCompetitions;
        private List<RoleMinimalDTO> incompatibleRoles;
    }

    @Data
    public static class RoleMinimalDTO {
        private Long id;
        private String roleName;
    }

    @Data
    public static class RoleCompetitionCreateDTO {
        @NotNull(message = "Competence ID is required")
        private Long competenceId;

        @NotNull(message = "Competence Importance ID is required")
        private Long competenceImportanceId;

        @NotNull(message = "Levels ID is required")
        private Long levelsId;
    }

    @Data
    public static class RoleCompetitionResponseDTO {
        private Long id;
        private CompetenceDTO.CompetenceMinimalDTO competence;
        private CompetenceImportanceDTO.CompetenceImportanceResponseDTO competenceImportance;
        private LevelsDTO.LevelsResponseDTO level;
    }
}