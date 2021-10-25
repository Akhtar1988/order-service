package com.ak.order.service.orders.serviceimp;

import com.ak.order.service.orders.constants.AppConstants;
import com.ak.order.service.orders.entities.Order;
import com.ak.order.service.orders.entities.Payment;
import com.ak.order.service.orders.model.OrderCancelResponse;
import com.ak.order.service.orders.model.OrderResponse;
import com.ak.order.service.orders.repositories.OrderRepo;
import com.ak.order.service.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public OrderResponse createOrder(Order order) {
        return getCreateOrder(order);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepo.findAll();
    }

    private OrderResponse getCreateOrder(Order order) {

        orderRepo.save(order);

        Payment payment = new Payment();
        payment.setId(order.getOrderId());
        payment.setFinalAmount(order.getQtyValue() * order.getTotalAmount());
        payment.setTransaction_id(UUID.randomUUID().toString());
        payment.setDate(order.getDate());

        payment = restTemplate.postForObject(AppConstants.MAKE_PAYMENT_URL, payment, Payment.class);
        if (payment != null) {
            payment.setPayment_status(AppConstants.SUCCESS_STATUS);
            order.setOrder_status(AppConstants.SUCCESS_STATUS);
        } else {
            payment.setPayment_status(AppConstants.FAILED);
            order.setOrder_status(AppConstants.FAILED);
        }
        OrderResponse response = new OrderResponse();
        response.setOrderId(payment.getId());
        response.setTotalAmt(payment.getFinalAmount());
        response.setStatus(payment.getPayment_status());
        response.setTransactionId(payment.getTransaction_id());
        response.setOrder(order);
        return response;
    }

    @Override
    public Order getViewById(Long id) {
        return orderRepo.getById(id);
    }

    @Override
    public OrderCancelResponse deleteOrder(Order order) {

        String status = "";
        if (order != null) {
            status = " Order cancelled";
            order.setOrder_status(status);
        } else {
            status = "Order failed.";
            order.setOrder_status(status);
        }
        orderRepo.deleteById((long) order.getOrderId());

        return new OrderCancelResponse(orderRepo.findAll(), status);
    }

    @Override
    public Payment makePayment(Payment payment) {
        payment.setPayment_status(paymentProcessing());
        payment.setTransaction_id(UUID.randomUUID().toString());
        return payment;
    }

    public String paymentProcessing() {
        //TODO HERE NEED TO INTEGRATE THE SDK PAYMENT GETAWAY ACCORDINGLY
        return new Random().nextBoolean() ? AppConstants.SUCCESS_STATUS : AppConstants.FAILED;
    }
}
