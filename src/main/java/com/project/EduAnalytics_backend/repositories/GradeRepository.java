package com.project.EduAnalytics_backend.repositories;

import com.project.EduAnalytics_backend.models.Grade;
import com.project.EduAnalytics_backend.models.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, String> {

    List<Grade> findByUser(User user);
}
