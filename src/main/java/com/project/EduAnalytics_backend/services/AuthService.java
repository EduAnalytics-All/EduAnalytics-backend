package com.project.EduAnalytics_backend.services;

import com.project.EduAnalytics_backend.models.User;


import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.EduAnalytics_backend.repositories.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

public String login(String email, String password) {

    User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Invalid email or password."));

    if (!passwordEncoder.matches(password, user.getPasswordHash()))
        throw new RuntimeException("Invalid email or password."); 
    
    return jwtService.generateToken(user);
    }

}
