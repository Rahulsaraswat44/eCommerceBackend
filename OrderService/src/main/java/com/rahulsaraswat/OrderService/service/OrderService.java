package com.rahulsaraswat.OrderService.service;

import com.rahulsaraswat.OrderService.model.OrderRequest;

public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);
}
