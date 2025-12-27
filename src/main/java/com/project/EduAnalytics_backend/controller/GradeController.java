package com.project.EduAnalytics_backend.controller;

import com.project.EduAnalytics_backend.dto.user.grade.GradeRequestDTO;
import com.project.EduAnalytics_backend.dto.user.grade.GradeResponseDTO;
import com.project.EduAnalytics_backend.models.Grade;
import com.project.EduAnalytics_backend.repositories.GradeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    GradeRepository gradeRepository;

    @PostMapping
    public ResponseEntity postGrade(@RequestBody @Valid GradeRequestDTO body) {
        Grade newGrade = new Grade(body);

        this.gradeRepository.save(newGrade);
        return ResponseEntity.ok(newGrade);
    }

    @GetMapping
    public ResponseEntity getAllGrades() {
        List<GradeResponseDTO> gradeList = this.gradeRepository
                .findAll()
                .stream()
                .map(GradeResponseDTO::new)
                .toList();

        return ResponseEntity.ok(gradeList);
    }
}
