package com.project.EduAnalytics_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.EduAnalytics_backend.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String>{
    UserDetails findByLogin(String login);
}
