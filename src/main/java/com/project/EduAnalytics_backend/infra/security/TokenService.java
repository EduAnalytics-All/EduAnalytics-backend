package com.project.EduAnalytics_backend.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.project.EduAnalytics_backend.models.User;


import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.time.Instant;


@Service
public class TokenService {

    private final TokenProperties tokenProperties;

    public TokenService(TokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    public String getSecret() {
        return tokenProperties.getSecret();
    }

    @PostConstruct
    public void validateSecret() {
        if (tokenProperties.getSecret() == null || tokenProperties.getSecret().isBlank()) {
            throw new IllegalStateException("JWT secret is not configured");
        }
    }

    public String generateToken(User user) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(tokenProperties.getSecret());
            System.out.println("SECRET GERAR TOKEN: " + tokenProperties.getSecret());

            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenProperties.getSecret());

            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant genExpirationDate() {
    return Instant.now().plusSeconds(7200);

    }
}
