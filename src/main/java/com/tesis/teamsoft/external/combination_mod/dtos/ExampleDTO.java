package com.tesis.teamsoft.external.combination_mod.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ExampleDTO {
    @Data
    public static class ExampleInputDTO {

        @NotBlank(message = "Message is required")
        private String message;
    }

    @Data
    public static class ExampleOutputDTO {

        @NotBlank(message = "Message is required")
        private String message;
        @NotNull(message = "Date is required")
        private Date date;
    }
}
