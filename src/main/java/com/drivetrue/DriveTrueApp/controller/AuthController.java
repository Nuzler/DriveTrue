package com.drivetrue.DriveTrueApp.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drivetrue.DriveTrueApp.entity.user;
import com.drivetrue.DriveTrueApp.repository.UserRepository;

@RestController
@RequestMapping()
@CrossOrigin(origins = "*")
public class AuthController {
       
    @Autowired
    private UserRepository userRepository;

 

    @PostMapping("/login")
    public Optional<user> login(@RequestBody user loginData) {
        
        return userRepository.findByUsernameAndPassword(loginData.getUsername(), loginData.getPassword());
               
    } 
}
