package com.rahulsaraswat.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private long orderId;
    private long amount;
    private String orderStatus;
    private Instant orderDate;
    private ProductDetails productDetails;
    private PaymentDetails paymentDetails;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static public class ProductDetails {
        private long productID;
        private String productName;
        private int price;
        private int quantity;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static public class PaymentDetails {
        private long id;
        private long orderId;
        private String paymentMode;
        private String referenceNumber;
        private Instant paymentDate;
        private String paymentStatus;
        private long amount;
    }

}
