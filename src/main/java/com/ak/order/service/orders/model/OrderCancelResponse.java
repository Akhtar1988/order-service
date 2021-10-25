package com.ak.order.service.orders.model;

import com.ak.order.service.orders.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCancelResponse {
    private List<Order> order;
    private String message;
}
