package com.drivetrue.DriveTrueApp.service;

import com.drivetrue.DriveTrueApp.entity.Order;
import com.drivetrue.DriveTrueApp.entity.OrderItem;
import com.drivetrue.DriveTrueApp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmailService emailService;

    public String processPayHereNotification(String orderIdStr, String statusCode) {
        try {
            Integer orderId = Integer.parseInt(orderIdStr);
            Optional <Order> orderOpt = orderRepository.getByOrderId(orderId);

            if (orderOpt.isPresent()) {
                Order order = orderOpt.get();

                if ("2".equals(statusCode)) {
                    order.setStates(2);
                    orderRepository.save(order);
                    System.out.println("Email:"+ order.getCustomer().getEmail() );
                 
                   StringBuilder itemsHtml = new StringBuilder();
                for (OrderItem item : order.getCartItems()) {
                      itemsHtml.append("<tr><td>").append(item.getFoodName()).append("</td><td style='text-align:right;'>x ")
                      .append(item.getQuantity()).append("</td></tr>");
                    }
                    System.out.println(itemsHtml);
                 
                   String emailBody =  """
    <html>
    <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;">
        <div style="background-color: white; padding: 20px; border-radius: 10px; max-width: 600px; margin: auto;">
            <h2 style="color: #333;">Thank you for your order, %s!</h2>
            <p>Your order has been received and is being processed.</p>
            <p><strong>Order ID:</strong> %s<br>
               <strong>Pickup Time:</strong> %s</p>
            <table width="100%%" style="border-collapse: collapse;">
                <thead>
                    <tr style="background-color: #eaeaea;">
                        <th style="text-align:left;">Item</th>
                        <th style="text-align:right;">Qty</th>
                    </tr>
                </thead>
                <tbody>
                    %s
                </tbody>
            </table>
            <p style="margin-top: 20px; font-size: 16px;"><strong>Total:</strong> Rs. %s</p>
            <p>https://travelerscafe.lk/order-status?states=success&uuid=%s&&order_id=%s</p>
            <p>See you soon at Travelers Cafe!</p>
        </div>
    </body>
    </html>
""".formatted(
    order.getCustomer().getFname(),
    order.getOrderId(),
    order.getCustomer().getPickupTime(),
    itemsHtml.toString(),
    order.getTotalPrice(),
    order.getUuid(),
    order.getOrderId()
);
                   
                   

                    
        
                    // Send email
                    emailService.sendOrderConfirmation(
                        order.getCustomer().getEmail(),
                        "Your Order Confirmation - Travelers Cafe",
                        emailBody
                    );
                    System.out.println("Email:"+ order.getCustomer().getEmail() );
                    return "New Order";
                } else {
                    order.setStates(0);
                    orderRepository.save(order);
                    return "Payment Fail";
                }

               
                
               

                
            } else {
                return "Order Not Found";
            }
        } catch (Exception e) {
            return "error";
        }
    }
}
