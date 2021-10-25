package com.ak.order.service.orders.model;

import com.ak.order.service.orders.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    Order order;
    private Integer orderId;
    private String transactionId;
    private String status;
    private Double totalAmt;

}
