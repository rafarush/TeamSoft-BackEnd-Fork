package com.tesis.teamsoft.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PersonDTO {

    @Data
    public static class PersonCreateDTO {
        @NotBlank(message = "Person name is required")
        private String personName;

        @NotBlank(message = "ID card is required")
        private String card;

        @NotBlank(message = "Surname is required")
        private String surName;

        @NotBlank(message = "Address is required")
        private String address;

        @NotBlank(message = "Phone is required")
        private String phone;

        @NotNull(message = "Sex is required")
        private Character sex;

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        private String email;

        @NotNull(message = "In date is required")
        private Date inDate;

        @NotNull(message = "Workload is required")
        private Float workload;

        @NotNull(message = "Experience is required")
        private Integer experience;

        @NotBlank(message = "Status is required")
        private String status;

        @NotNull(message = "Birth date is required")
        private Date birthDate;

        // IDs de entidades relacionadas
        @NotNull(message = "County ID is required")
        private Long county;

        @NotNull(message = "Race ID is required")
        private Long race;

        @NotNull(message = "Person group ID is required")
        private Long group;

        @NotNull(message = "Nacionality ID is required")
        private Long nacionality;

        @NotNull(message = "Religion ID is required")
        private Long religion;

        // Listas de relaciones
        @Valid
        private List<CompetenceValueCreateDTO> competenceValues;

        @Valid
        private List<PersonalInterestCreateDTO> personalInterests;

        @Valid
        private List<PersonalProjectInterestCreateDTO> personalProjectInterests;

        @Valid
        @NotNull(message = "Person test is required")
        private PersonTestCreateDTO personTest;

        @Valid
        private List<PersonConflictCreateDTO> personConflicts;
    }

    @Data
    public static class PersonResponseDTO {
        private Long id;
        private String personName;
        private String card;
        private String surName;
        private String address;
        private String phone;
        private Character sex;
        private String email;
        private Date inDate;
        private Float workload;
        private Integer experience;
        private String status;
        private Date birthDate;
        private Integer age;
        private CountyDTO.CountyResponseDTO county;
        private RaceDTO.RaceResponseDTO race;
        private PersonGroupDTO.PersonGroupResponseDTO group;
        private NacionalityDTO.NacionalityResponseDTO nacionality;
        private ReligionDTO.ReligionResponseDTO religion;
        private AgeGroupDTO.AgeGroupResponseDTO ageGroup;
        private List<CompetenceValueResponseDTO> competenceValues;
        private List<PersonalInterestResponseDTO> personalInterests;
        private List<PersonalProjectInterestResponseDTO> personalProjectInterests;
        private PersonTestResponseDTO personTest;
        private List<PersonConflictResponseDTO> personConflicts;
    }

    // DTOs para listas
    @Data
    public static class CompetenceValueCreateDTO {
        @NotNull(message = "Competence ID is required")
        private Long competenceId;

        @NotNull(message = "Levels ID is required")
        private Long levelsId;
    }

    @Data
    public static class CompetenceValueResponseDTO {
        private Long id;
        private CompetenceDTO.CompetenceMinimalDTO competence;
        private LevelsDTO.LevelsResponseDTO level;
    }

    @Data
    public static class PersonalInterestCreateDTO {
        @NotNull(message = "Role ID is required")
        private Long roleId;

        @NotNull(message = "Preference is required")
        private Boolean preference;
    }

    @Data
    public static class PersonalInterestResponseDTO {
        private Long id;
        private RoleDTO.RoleMinimalDTO role;
        private Boolean preference;
    }

    @Data
    public static class PersonalProjectInterestCreateDTO {
        @NotNull(message = "Project ID is required")
        private Long projectId;

        @NotNull(message = "Preference is required")
        private Boolean preference;
    }

    @Data
    public static class PersonalProjectInterestResponseDTO {
        private Long id;
        private ProjectMinimalDTO project;
        private Boolean preference;
    }

    @Data
    public static class ProjectMinimalDTO {
        private Long id;
        private String projectName;
    }

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

    @Data
    public static class PersonConflictCreateDTO {
        @NotNull(message = "Conflict index ID is required")
        private Long conflictIndexId;

        @NotNull(message = "Person conflict ID is required")
        private Long personConflictId;
    }

    @Data
    public static class PersonConflictResponseDTO {
        private Long id;
        private ConflictIndexDTO.ConflictIndexResponseDTO conflictIndex;
        private PersonMinimalDTO personConflict;
    }

    @Data
    public static class PersonMinimalDTO {
        private Long id;
        private String personName;
        private String idCard;
    }
}