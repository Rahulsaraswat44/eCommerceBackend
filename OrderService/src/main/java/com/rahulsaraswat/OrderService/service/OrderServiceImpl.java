package com.rahulsaraswat.OrderService.service;

import com.rahulsaraswat.OrderService.entity.Order;
import com.rahulsaraswat.OrderService.exception.CustomOrderException;
import com.rahulsaraswat.OrderService.external.client.PaymentService;
import com.rahulsaraswat.OrderService.external.client.ProductService;
import com.rahulsaraswat.OrderService.external.request.PaymentRequest;
import com.rahulsaraswat.OrderService.external.response.PaymentResponse;
import com.rahulsaraswat.OrderService.external.response.ProductResponse;
import com.rahulsaraswat.OrderService.model.OrderRequest;
import com.rahulsaraswat.OrderService.model.OrderResponse;
import com.rahulsaraswat.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
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

    @Override
    public OrderResponse getOrder(long orderId) {
        log.info("Getting details for orderId: {}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomOrderException("Order  Not found for orderId:" + orderId,
                        "NOT_FOUND", 404));

        ProductResponse productResponse = productService.getProductById(order.getProductId()).getBody();

        OrderResponse.ProductDetails productDetails = OrderResponse.ProductDetails.builder()
                .productName(productResponse.getProductName())
                .productID(productResponse.getProductID())
                .price(productResponse.getPrice())
                .quantity(productResponse.getQuantity())
                .build();

        PaymentResponse paymentResponse = paymentService.getPaymentDetails(orderId).getBody();
        OrderResponse.PaymentDetails paymentDetails = OrderResponse.PaymentDetails.builder()
                .id(paymentResponse.getId())
                .paymentStatus(paymentResponse.getPaymentStatus())
                .referenceNumber(paymentResponse.getReferenceNumber())
                .paymentDate(paymentResponse.getPaymentDate())
                .amount(paymentResponse.getAmount())
                .orderId(paymentResponse.getOrderId())
                .paymentMode(paymentResponse.getPaymentMode())
                .build();

        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(order.getId())
                .amount(order.getPrice())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getStatus())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();

        return orderResponse;
    }
}
