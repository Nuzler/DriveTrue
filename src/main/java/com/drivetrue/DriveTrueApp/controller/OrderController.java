package com.drivetrue.DriveTrueApp.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drivetrue.DriveTrueApp.dto.OrderRequest;
import com.drivetrue.DriveTrueApp.entity.Order;
import com.drivetrue.DriveTrueApp.entity.OrderItem;
import com.drivetrue.DriveTrueApp.service.OrderService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

import com.drivetrue.PayHereHashGenerator;




@RestController
@CrossOrigin(origins = {"http://localhost:5173","https://drive-true-front-end.vercel.app","https://travelerscafe.lk","https://drive-true-dash-bord-3zgw.vercel.app"})
public class OrderController {
    
    @Autowired
    OrderService orderService;



   @PostMapping("/adding")
   public ResponseEntity<?> saveOrder(@RequestBody OrderRequest request){
       
     Order savedOrder =orderService.saveOrder(request);

     String merchantId = "244546";
     String merchantSecret = "MzcxMDUxMTc0ODQzMzMzOTg5NTI5NTc3MzkzMjcyNDQzNTU3Mjg1";
     // if you have total amount
     String currency = "LKR";
     String orderId = savedOrder.getOrderId().toString();
     String amount=request.getTotalPrice().toString();
     String hash = PayHereHashGenerator.generateHash(merchantId, orderId,amount,currency, merchantSecret);
   
    
          Map<String, Object> response = new HashMap<>();
        response.put("message", "Order placed successfully");
        response.put("orderId", savedOrder.getOrderId());
        response.put("uuid",savedOrder.getUuid());
        response.put("hash", hash);
        

       


        return ResponseEntity.ok( response);
}


@GetMapping("/GetOrders")
public List<Order> getOrders(){
    
     return orderService.getOrders();

}




@GetMapping("/GetOrdersby/{states}")
public List<Order> getOrderByStatus(@PathVariable Integer states)
{
     
     
     return orderService.getOrderByStatus(states);
}



@GetMapping("/GetOrderById/{orderId}")
public Order getOrderById(@PathVariable Integer orderId){

     return orderService.getOrderById(orderId);
}


@PutMapping("/orders/{orderId}/states")
public Order changeStatus(@PathVariable Integer orderId,@RequestParam Integer states ){

     orderService.changeStatus(orderId,states);

     return null;
}

@GetMapping("/Orders/{date}")
public List<Order> getOrdersbyDate(@PathVariable LocalDate date){

     return orderService.getOrdersbyDate(date);
}




    
}
