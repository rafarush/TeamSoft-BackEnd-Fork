package com.tesis.teamsoft.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class CompetenceDTO {

    @Data
    public static class CompetenceCreateDTO{
        @Pattern(regexp = "^[\\p{L}\\s]+$", message = "Only letters and spaces are allowed")
        @NotBlank(message = "Competence name is required")
        private String competitionName;

        @Pattern(regexp = "^[\\p{L}\\s]+$", message = "Only letters and spaces are allowed")
        @NotBlank(message = "Competence description is required")
        private String description;

        @NotNull(message = "Define if technical competency")
        private Boolean technical;

        List<CompetenceDimensionDTO.CompetenceDimensionCreateDTO> dimensionList;
    }

    @Data
    public static class CompetenceResponseDTO{
        private Long id;
        private String competitionName;
        private String description;
        private Boolean technical;

        List<CompetenceDimensionDTO.CompetenceDimensionResponseDTO> dimensionList;
    }

    @Data
    public static class CompetenceMinimalDTO {
        private Long id;
        private String competitionName;
        private String description;
        private Boolean technical;
    }
}
