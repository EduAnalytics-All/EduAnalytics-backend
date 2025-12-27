package com.project.EduAnalytics_backend.dto.user.grade;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GradeRequestDTO (

    @NotBlank
    String name,

    @NotNull
    String grade
)
{
}
