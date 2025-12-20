package com.project.EduAnalytics_backend.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.EduAnalytics_backend.models.User;
import com.project.EduAnalytics_backend.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String name, String email, String password, String type) {

        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Required name");
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Required email");
        if (password == null || password.length() < 8)
            throw new IllegalArgumentException("Weak password (min 8)");
        if (type == null || type.isBlank())
            throw new IllegalArgumentException("Required type (ESTUDANTE or PROFESSOR)");

        String normalizedEmail = email.trim().toLowerCase();

        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalStateException("Email already registered");
        }

        User user = new User();
        user.setName(name.trim());
        user.setEmail(normalizedEmail);

        user.setRole(resolveRole(type, normalizedEmail));

        user.setPasswordHash(encoder.encode(password));

        return userRepository.save(user);
    }

    private User.Role resolveRole(String type, String email) {
        if ("PROFESSOR".equalsIgnoreCase(type)) {
            if (!email.endsWith("@school.br")) {
                throw new IllegalArgumentException(
                        "Only institutional emails addresses can be used as a PROFESSOR's email address.");
            }
            return User.Role.PROFESSOR;
        }
        if ("ALUNO".equalsIgnoreCase(type)) {
            return User.Role.ALUNO;
        }
        throw new IllegalArgumentException("Invalid type: use ALUNO or PROFESSOR");
    }
}
