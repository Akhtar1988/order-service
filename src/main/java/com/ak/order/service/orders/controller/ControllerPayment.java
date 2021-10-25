package com.ak.order.service.orders.controller;

import com.ak.order.service.orders.entities.Payment;
import com.ak.order.service.orders.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/payment")
public class ControllerPayment {

     private final OrderService orderService;

   @PostMapping("/makePayment")
    public Payment makePayment(@RequestBody Payment payment){
       return orderService.makePayment(payment);
    }
}
