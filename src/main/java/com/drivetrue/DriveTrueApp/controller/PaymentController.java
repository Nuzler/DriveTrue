package com.drivetrue.DriveTrueApp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.drivetrue.DriveTrueApp.service.PaymentService;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.HashMap;
import java.util.Map;

import com.drivetrue.DriveTrueApp.entity.Order;
import com.drivetrue.DriveTrueApp.service.OrderService;

@RestController
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private OrderService orderService;

    @PostMapping("/payhere-notify")
    public ResponseEntity<String> handleNotify(@RequestParam Map<String, String> params) {
        String orderIdStr = params.get("order_id");
        String statusCode = params.get("status_code");
        
        System.out.print("im inside notyfy");

        if (orderIdStr == null || statusCode == null) {
            return ResponseEntity.badRequest().body("Invalid request");
        }

       String result = paymentService.processPayHereNotification(orderIdStr,statusCode);
        
      

        if ("New Order".equals(result)) {
            
            Map<String, String> message = new HashMap<>();
            message.put("orderId", orderIdStr);
            messagingTemplate.convertAndSend("/topic/new-order", message);
 
            return ResponseEntity.ok("OK");
          
            
        }else if("Payment Fail".equals(result)){
            return ResponseEntity.status(300).body("Payment Not Sucess");
        }
        else if ("Order Not Found".equals(result)) {
            return ResponseEntity.status(404).body("Order Not Found");
        }
        else {
            return ResponseEntity.status(500).body("Error");
        }
    }
}    