package com.rahulsaraswat.OrderService.service;

import com.rahulsaraswat.OrderService.entity.Order;
import com.rahulsaraswat.OrderService.external.client.ProductService;
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
    OrderRepository orderRepository;

    @Autowired
    ProductService productService;
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
        log.info("order created successfully...");

        return order.getId();
    }
}
