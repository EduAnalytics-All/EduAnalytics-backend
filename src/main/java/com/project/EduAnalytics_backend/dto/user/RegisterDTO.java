package com.project.EduAnalytics_backend.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
        @NotBlank(message = "name is required")
        String name,

        @NotBlank(message = "email is required")
        @Email(message = "email must be valid")
        String email,

        @NotBlank(message = "password is required")
        @Size(min = 8, message = "password must be at least 8 characters")
        String password
) {
}
