package com.rahulsaraswat.OrderService.service;

import com.rahulsaraswat.OrderService.entity.Order;
import com.rahulsaraswat.OrderService.external.client.PaymentService;
import com.rahulsaraswat.OrderService.external.client.ProductService;
import com.rahulsaraswat.OrderService.external.request.PaymentRequest;
import com.rahulsaraswat.OrderService.model.OrderRequest;
import com.rahulsaraswat.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;
    @Override
    public Long placeOrder(OrderRequest orderRequest) {
        // find if the product exists with the product ID
        // if the product exist, reduce the quantity by the the requested quantity.
        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
        // make the payment request to payment service.
        // save the order in database.

        log.info("Creating order with status CREATED");
        Order order = Order.builder()
                .productId(orderRequest.getProductId())
                .price(orderRequest.getPrice())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .status("CREATED")
                .build();

        orderRepository.save(order);
        log.info("calling payment service to make the payment");

        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMethod().name())
                .amount((int)orderRequest.getPrice())
                .build();

        String orderStatus = null;

        try {
            paymentService.doPayment(paymentRequest);
            log.info("payment done successfully");
            orderStatus = "PLACED";
        } catch (Exception e) {
            log.info("Could not make the payment");
            orderStatus = "PAYMENT_FAILED";
        }

        order.setStatus(orderStatus);
        orderRepository.save(order);
        return order.getId();
    }
}
