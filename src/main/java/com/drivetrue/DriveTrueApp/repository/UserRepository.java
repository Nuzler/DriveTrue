package com.drivetrue.DriveTrueApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drivetrue.DriveTrueApp.entity.user;

public interface  UserRepository extends JpaRepository<user, Integer> {

    Optional<user> findByUsernameAndPassword(String username, String password);

    public Optional<user> findByUsername(String username);
    
}
