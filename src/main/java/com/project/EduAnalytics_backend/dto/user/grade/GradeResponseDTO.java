package com.project.EduAnalytics_backend.dto.user.grade;


import com.project.EduAnalytics_backend.models.Grade;

public record GradeResponseDTO (
        String id,
        String name,
        Integer grade
)
{
    public  GradeResponseDTO (Grade grade){
        this(grade.getId(), grade.getName(), grade.getGrade());
    }
}
