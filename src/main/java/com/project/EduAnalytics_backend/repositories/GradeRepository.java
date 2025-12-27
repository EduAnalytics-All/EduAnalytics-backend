package com.project.EduAnalytics_backend.repositories;

import com.project.EduAnalytics_backend.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, String> {
}
