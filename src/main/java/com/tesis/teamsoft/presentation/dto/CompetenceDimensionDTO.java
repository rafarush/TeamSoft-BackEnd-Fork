package com.tesis.teamsoft.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompetenceDimensionDTO {

    @Data
    public static class CompetenceDimensionCreateDTO{
        @NotBlank(message = "Competence Dimension name is required")
        private String name;

        @NotNull(message = "Levels ID is required")
        private Long levelsID;
    }

    @Data
    public static class CompetenceDimensionResponseDTO{
        private String name;
        private Long competenceID;
        private LevelsDTO.LevelsResponseDTO levelsFk;
    }
}
