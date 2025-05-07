package com.drivetrue.DriveTrueApp.dto;

import lombok.Data;

@Data
public class OrderItemRequest {

    private int foodId;
    private String foodName;
    private int quantity;
    private int price;
    private String imageUrl;
    
    
}
