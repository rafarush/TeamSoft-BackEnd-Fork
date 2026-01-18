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
        private List<CompetenceValueDTO.CompetenceValueCreateDTO> competenceValues;

        @Valid
        private List<PersonalInterestDTP.PersonalInterestCreateDTO> personalInterests;

        @Valid
        private List<PersonalProjectInterestDTO.PersonalProjectInterestCreateDTO> personalProjectInterests;

        @Valid
        @NotNull(message = "Person test is required")
        private PersonTestDTO.PersonTestCreateDTO personTest;

        @Valid
        private List<PersonConflictDTO.PersonConflictCreateDTO> personConflicts;
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
        private List<CompetenceValueDTO.CompetenceValueResponseDTO> competenceValues;
        private List<PersonalInterestDTP.PersonalInterestResponseDTO> personalInterests;
        private List<PersonalProjectInterestDTO.PersonalProjectInterestResponseDTO> personalProjectInterests;
        private PersonTestDTO.PersonTestResponseDTO personTest;
        private List<PersonConflictDTO.PersonConflictResponseDTO> personConflicts;
    }

    @Data
    public static class PersonMinimalDTO {
        private Long id;
        private String personName;
        private String surName;
        private String card;
    }
}