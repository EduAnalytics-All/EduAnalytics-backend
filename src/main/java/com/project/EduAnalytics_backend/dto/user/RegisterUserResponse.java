package com.project.EduAnalytics_backend.dto.user;

import java.util.UUID;
import com.project.EduAnalytics_backend.models.User;

public class RegisterUserResponse {
    public UUID id;
    public String name;
    public String email;
    public User.Role role;

    public RegisterUserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
