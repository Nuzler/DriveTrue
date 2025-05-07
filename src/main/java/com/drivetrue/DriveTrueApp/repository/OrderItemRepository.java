package com.drivetrue.DriveTrueApp.repository;

import com.drivetrue.DriveTrueApp.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  OrderItemRepository extends JpaRepository <OrderItem, Integer> {

    
}
