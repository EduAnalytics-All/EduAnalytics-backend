package com.project.EduAnalytics_backend.controller;

import com.project.EduAnalytics_backend.dto.user.grade.GradeRequestDTO;
import com.project.EduAnalytics_backend.dto.user.grade.GradeResponseDTO;
import com.project.EduAnalytics_backend.models.Grade;
import com.project.EduAnalytics_backend.models.User;
import com.project.EduAnalytics_backend.repositories.GradeRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    GradeRepository gradeRepository;

    @PostMapping
    public ResponseEntity<?> createGrade(@RequestBody @Valid GradeRequestDTO data) {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Grade grade = new Grade(data, user);

        gradeRepository.save(grade);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Grade>> getMyGrades(){

            User user = (User) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

        List<Grade> grades = gradeRepository.findByUser(user);

        return ResponseEntity.ok(grades);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<GradeResponseDTO>> getAllGrades(){

        List<GradeResponseDTO> gradeList = gradeRepository
                .findAll()
                .stream()
                .map(GradeResponseDTO::new)
                .toList();

        return ResponseEntity.ok(gradeList);
    }


    @GetMapping("/me")
    public ResponseEntity<String> me(Authentication authentication) {
        return ResponseEntity.ok(authentication.getAuthorities().toString());
}
}
