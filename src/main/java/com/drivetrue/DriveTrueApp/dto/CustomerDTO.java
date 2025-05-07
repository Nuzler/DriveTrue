package com.drivetrue.DriveTrueApp.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CustomerDTO {
    
    private String fname;
    private String lname;
    private String vehicle;
    private int number;
    private String email;
    private LocalDateTime pickupTime; 

}
