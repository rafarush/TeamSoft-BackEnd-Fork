package com.tesis.teamsoft.presentation.dto;


import com.tesis.teamsoft.persistence.entity.auxiliar.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRoleDTO {

    @Data
    public static class UserRoleCreateDTO {

        @NotNull(message = "The rol name can't be empty")
        @Enumerated(EnumType.STRING)
        private Roles name;

    }


    @Data
    public static class UserRoleResponseDTO {

        private Long id;

        private Roles name;
    }
}
