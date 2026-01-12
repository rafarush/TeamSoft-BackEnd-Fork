package com.tesis.teamsoft.presentation.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {

    @Data
    public static class UserCreateDTO {
        @NotBlank(message = "Person name is required")
        @Size(min = 1, max = 50, message = "Person name must be between 1 and 50 characters")
        private String personName;

        @NotBlank(message = "Surname is required")
        @Size(min = 1, max = 50, message = "Surname must be between 1 and 50 characters")
        private String surname;

        @NotBlank(message = "ID card is required")
        @Size(min = 1, max = 50, message = "ID card must be between 1 and 50 characters")
        private String idCard;

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        @Size(min = 1, max = 50, message = "Email must be between 1 and 50 characters")
        private String mail;

        @NotNull(message = "Enabled status is required")
        private boolean enabled;

//        @NotNull(message = "At least one role is required")
//        @Size(min = 1, message = "At least one role must be selected")
        private Set<Long> roleIds;
    }

    @Data
    public static class UserResponseDTO {
        private Long id;
        private String personName;
        private String surname;
        private String idCard;
        private String mail;
        private String username;
        private boolean enabled;
        private Set<RoleResponseDTO> roles;
    }

    @Data
    public static class RoleResponseDTO {
        private Long id;
        private String roleName;
    }
}