package com.drivetrue.DriveTrueApp.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.drivetrue.DriveTrueApp.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> getBystates(Integer states); 
    Order getByorderId(Integer orderId);
    Optional<Order> getByOrderId(Integer orderId);
    List<Order>getByCustomerPickupTime(LocalDateTime pickupTime );

    @Query(value = "SELECT * FROM `order` o WHERE o.pickup_time BETWEEN :start AND :end", nativeQuery = true)
    List<Order> findByPickupTimeNative(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
    
   
    
}
