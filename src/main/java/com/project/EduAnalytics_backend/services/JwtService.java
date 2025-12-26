package com.project.EduAnalytics_backend.services;

import org.springframework.stereotype.Service;

@Service
public class JwtService {
    
    private static final String SECRET_KEY = "edu-analytics-secret-key";

    public String generateToken(User user){

        return Jwts.buider().setsubject(user.getId().toString())
        .claim("email", user.getEmail())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 dia
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
    }

}
