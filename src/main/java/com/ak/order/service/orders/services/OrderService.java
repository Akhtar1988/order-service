package com.ak.order.service.orders.services;

import com.ak.order.service.orders.entities.Order;
import com.ak.order.service.orders.entities.Payment;
import com.ak.order.service.orders.model.OrderCancelResponse;
import com.ak.order.service.orders.model.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(Order orderRequest);

    List<Order> getAllOrder();

    Order getViewById(Long id);

    OrderCancelResponse deleteOrder(Order order);

    Payment makePayment(Payment payment);
}
