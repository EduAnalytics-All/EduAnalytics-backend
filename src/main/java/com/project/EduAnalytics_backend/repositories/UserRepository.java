package com.project.EduAnalytics_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.EduAnalytics_backend.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>{

    Optional<User> findByEmail(String email);
}
