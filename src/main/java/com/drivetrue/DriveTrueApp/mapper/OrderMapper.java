package com.drivetrue.DriveTrueApp.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.drivetrue.DriveTrueApp.dto.CustomerDTO;
import com.drivetrue.DriveTrueApp.dto.OrderItemRequest;
import com.drivetrue.DriveTrueApp.dto.OrderRequest;
import com.drivetrue.DriveTrueApp.entity.Customer;
import com.drivetrue.DriveTrueApp.entity.Order;
import com.drivetrue.DriveTrueApp.entity.OrderItem;

public class OrderMapper {

       
    public static Customer toCustomer(CustomerDTO dto){
     
        Customer customer = new Customer();
          customer.setFname(dto.getFname());
          customer.setLname(dto.getLname());
          customer.setVehicle(dto.getVehicle());
          customer.setNumber(dto.getNumber());
          customer.setEmail(dto.getEmail());
          customer.setPickupTime(dto.getPickupTime());
          customer.setOrderTime(dto.getOrderTime());
          customer.setPickupOption(dto.getPickupOption());
          
        return customer;
    
      }
    
      public static Order toEntity(OrderRequest dto){
        Order order =new Order();
        Customer customer=toCustomer(dto.getCustomer());
         order.setStates(dto.getStates());
         order.setUuid(dto.getUuid());
         order.setTotalPrice(dto.getTotalPrice());
        List<OrderItem> items= dto.getCartItems().stream().map(OrderItemRequest->{
            OrderItem item = new OrderItem();
            item.setFoodId(OrderItemRequest.getFoodId());
            item.setFoodName(OrderItemRequest.getFoodName());
            item.setPrice(OrderItemRequest.getPrice());
            item.setQuantity(OrderItemRequest.getQuantity());
            item.setImageUrl(OrderItemRequest.getImageUrl());
            item.setOrder(order);
            return item;
        }).collect(Collectors.toList());

        order.setCustomer(customer);
        order.setCartItems(items);

        return order;
      }


      public static CustomerDTO toCustomerDTO(Customer customer){
        CustomerDTO cdto = new CustomerDTO();
          cdto.setFname(customer.getFname());
          cdto.setLname(customer.getLname());
          cdto.setVehicle(customer.getVehicle());
          cdto.setNumber(customer.getNumber());
          cdto.setEmail(customer.getEmail());
          cdto.setPickupTime(customer.getPickupTime());
          cdto.setPickupOption(customer.getPickupOption());


        return cdto;
      }

      
      public static OrderRequest toDTO(Order order){
            
           OrderRequest dto = new OrderRequest();
            dto.setOrderId(order.getOrderId());

            return dto;
      }


        

        
    }
    

