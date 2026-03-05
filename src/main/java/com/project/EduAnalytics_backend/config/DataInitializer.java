package com.project.EduAnalytics_backend.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.project.EduAnalytics_backend.models.User;
import com.project.EduAnalytics_backend.models.enums.UserRole;
import com.project.EduAnalytics_backend.repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {

        if (userRepository.findByEmail("admin@edu.com").isEmpty()) {

            User admin = new User();
            admin.setName("Administrador");
            admin.setEmail("admin@edu.com");
            admin.setPassword(passwordEncoder.encode("12345678"));
            admin.setRole(UserRole.ADMIN);

            userRepository.save(admin);
        }
    }
    
}
