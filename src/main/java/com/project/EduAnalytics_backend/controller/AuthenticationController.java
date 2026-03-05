package com.project.EduAnalytics_backend.controller;


import com.project.EduAnalytics_backend.dto.user.AuthenticationDTO;
import com.project.EduAnalytics_backend.dto.user.LoginResponseDTO;
import com.project.EduAnalytics_backend.dto.user.RegisterDTO;
import com.project.EduAnalytics_backend.infra.security.TokenService;
import com.project.EduAnalytics_backend.models.User;
import com.project.EduAnalytics_backend.models.enums.UserRole;
import com.project.EduAnalytics_backend.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO data){
    if (this.userRepository.findByEmail(data.email()).isPresent()) {
        return ResponseEntity.badRequest().build();
    }

    String encryptedPassword = passwordEncoder.encode(data.password());
    User newUser = new User(
            data.email(),
            data.name(),
            encryptedPassword,
            UserRole.USER
    );

    this.userRepository.save(newUser);

    return ResponseEntity.ok().build();
    }
}
