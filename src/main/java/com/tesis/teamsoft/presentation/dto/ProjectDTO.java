package com.tesis.teamsoft.presentation.dto;

import lombok.Data;

@Data
public class ProjectDTO {

    @Data
    public static class ProjectResponseDTO {
        private long id;
        private String projectName;
    }
}
