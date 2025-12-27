package com.project.EduAnalytics_backend.dto.user;

import com.project.EduAnalytics_backend.models.UserRole;

public record RegisterDTO(
        String login,
        String password,
        UserRole role
)
{
}
