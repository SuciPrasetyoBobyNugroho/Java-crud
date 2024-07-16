package com.example.models.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.entities.AppUser;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    
    Optional<AppUser> findByEmail(String email);
}
