package com.project.EduAnalytics_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.EduAnalytics_backend.dto.user.RegisterUserRequest;
import com.project.EduAnalytics_backend.dto.user.RegisterUserResponse;
import com.project.EduAnalytics_backend.models.User;
import com.project.EduAnalytics_backend.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest body){
        User created = userService.register(body.name,body.email, body.password, body.type);

        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(created.getId())
        .toUri();
        
        return ResponseEntity.created(location).body(new RegisterUserResponse(created));
    }

}
