package com.rahulsaraswat.OrderService.service;

import com.rahulsaraswat.OrderService.model.OrderRequest;
import com.rahulsaraswat.OrderService.model.OrderResponse;

public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrder(long orderId);
}
