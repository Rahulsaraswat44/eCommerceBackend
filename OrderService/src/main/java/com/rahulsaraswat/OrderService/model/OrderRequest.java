package com.rahulsaraswat.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    private long productId;
    private int quantity;
    private long price;
    private PaymentMethod paymentMethod;
}
