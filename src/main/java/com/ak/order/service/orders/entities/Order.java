package com.ak.order.service.orders.entities;

import com.ak.order.service.orders.model.OrderResponse;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private int orderId;

    private String date;

    private int qtyValue;

    private String order_status;

    @Column(name = "total_price")
    private Double totalAmount;
}
