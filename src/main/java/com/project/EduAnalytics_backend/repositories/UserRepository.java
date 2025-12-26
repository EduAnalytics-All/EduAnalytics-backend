package com.project.EduAnalytics_backend.repositories;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.EduAnalytics_backend.models.User;

public interface UserRepository extends JpaRepository<User, UUID>{
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

}
