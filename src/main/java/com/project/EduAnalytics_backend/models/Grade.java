package com.project.EduAnalytics_backend.models;

import com.project.EduAnalytics_backend.dto.user.grade.GradeRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "grade")
@Entity(name = "grade")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private String id;

    private String name;

    private Float grade;

    public Grade(GradeRequestDTO data){
        this.name = data.name();
        this.grade = Float.valueOf(data.grade());
    }
}
