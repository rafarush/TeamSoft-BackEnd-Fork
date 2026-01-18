package com.tesis.teamsoft.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class PersonTestDTO {

    @Data
    public static class PersonTestCreateDTO {
        @NotNull(message = "ES is required")
        @JsonProperty("e_S")
        private Character e_S;

        @NotNull(message = "ID is required")
        @JsonProperty("i_D")
        private Character i_D;

        @NotNull(message = "CO is required")
        @JsonProperty("c_O")
        private Character c_O;

        @NotNull(message = "IS is required")
        @JsonProperty("i_S")
        private Character i_S;

        @NotNull(message = "CE is required")
        @JsonProperty("c_E")
        private Character c_E;

        @NotNull(message = "IR is required")
        @JsonProperty("i_R")
        private Character i_R;

        @NotNull(message = "ME is required")
        @JsonProperty("m_E")
        private Character m_E;

        @NotNull(message = "CH is required")
        @JsonProperty("c_H")
        private Character c_H;

        @NotNull(message = "IF is required")
        @JsonProperty("i_F")
        private Character i_F;

        @NotBlank(message = "MBTI test result is required")
        private String tipoMB;
    }

    @Data
    public static class PersonTestResponseDTO {
        private Long id;
        private Character e_S;
        private Character i_D;
        private Character c_O;
        private Character i_S;
        private Character c_E;
        private Character i_R;
        private Character m_E;
        private Character c_H;
        private Character i_F;
        private String tipoMB;
    }
}
