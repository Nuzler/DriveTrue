package com.drivetrue.DriveTrueApp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String fname;
    private String lname;
    private String vehicle;
    private int number;
    private String email;
    private LocalDateTime pickupTime;  
}
