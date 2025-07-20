package com.drivetrue.DriveTrueApp.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.drivetrue.DriveTrueApp.repository.OrderItemRepository;
import com.drivetrue.DriveTrueApp.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drivetrue.DriveTrueApp.dto.CustomerDTO;
import com.drivetrue.DriveTrueApp.dto.OrderItemRequest;
import com.drivetrue.DriveTrueApp.dto.OrderRequest;
import com.drivetrue.DriveTrueApp.entity.Customer;
import com.drivetrue.DriveTrueApp.entity.Order;
import com.drivetrue.DriveTrueApp.entity.OrderItem;

@Service
public class OrderService {

 

    @Autowired      
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

   public Order saveOrder(OrderRequest request){
    
       Order order =new Order();
       order.setStates(request.getStates());
       order.setTotalPrice(request.getTotalPrice());

       Customer customer = new Customer();

       customer.setFname(request.getCustomer().getFname());
       customer.setLname(request.getCustomer().getLname());
       customer.setNumber(request.getCustomer().getNumber());
       customer.setEmail(request.getCustomer().getEmail());
       customer.setPickupTime(request.getCustomer().getPickupTime());
       customer.setVehicle(request.getCustomer().getVehicle());
       ZoneId sriLankaZone = ZoneId.of("Asia/Colombo");
        LocalDateTime colomboTime = ZonedDateTime.now(sriLankaZone).toLocalDateTime();
        customer.setOrderTime(colomboTime);
       
       order.setCustomer(customer);

       Order savedOrder=orderRepository.save(order);

      for (OrderItemRequest itemreq:request.getCartItems()){
        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setFoodId(itemreq.getFoodId());
        item.setFoodName(itemreq.getFoodName());
        item.setPrice(itemreq.getPrice());
        item.setQuantity(itemreq.getQuantity());
        item.setImageUrl(itemreq.getImageUrl());
        
        orderItemRepository.save(item);
  
      }
    return savedOrder;
             
   }


   public List<Order> getOrders(){
  
    return orderRepository.findAll();

   }


   public List<Order>getOrderByStatus(Integer states){
     
      return orderRepository.getBystates(states);

   }

   public Order getOrderById(Integer orderIdStr){

    return orderRepository.getByorderId(orderIdStr);
   }

   public Order changeStatus(Integer orderId,Integer newStates){

       Order order = orderRepository.getByorderId(orderId);
       order.setStates(newStates);
       orderRepository.save(order);

         return null;
   }
   
   public List<Order> getOrdersbyDate(LocalDate date){

      LocalDateTime startTime=date.atStartOfDay();
      LocalDateTime endTime=date.atTime(LocalTime.MAX);

    return  orderRepository.findByPickupTimeNative(startTime,endTime);
   }
   
  
   
}
