package com.ak.order.service.orders.repositories;

import com.ak.order.service.orders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
