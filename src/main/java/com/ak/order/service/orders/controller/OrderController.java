package com.ak.order.service.orders.controller;

import com.ak.order.service.orders.entities.Order;
import com.ak.order.service.orders.model.OrderCancelResponse;
import com.ak.order.service.orders.model.OrderResponse;
import com.ak.order.service.orders.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("api/order")
@RestController
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create_order")
    public OrderResponse createOrder(@RequestBody Order order) {
           return orderService.createOrder(order);

    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/viewAllOrderDetails")
    public List<Order> getAllOrder(){
        return orderService.getAllOrder();

    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getViewById/{id}")
     public Order getOrderById(@PathVariable("id") Long id){
        return orderService.getViewById(id);
     }

    @ResponseStatus(HttpStatus.OK)
     @DeleteMapping(name = "/deleteOrder/{order_id}")
     public OrderCancelResponse deleteOrder(@RequestBody Order order){
        return orderService.deleteOrder(order);
     }
}
