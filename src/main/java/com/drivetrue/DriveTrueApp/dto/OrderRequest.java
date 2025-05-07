package com.drivetrue.DriveTrueApp.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {
    private int orderId;
    private String uuid;
    private Integer states;
    private Integer totalPrice;
    private CustomerDTO customer;
    private List<OrderItemRequest> cartItems;
}
