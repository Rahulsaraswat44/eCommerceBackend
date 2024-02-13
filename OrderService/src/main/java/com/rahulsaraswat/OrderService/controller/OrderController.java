package com.rahulsaraswat.OrderService.controller;

import com.rahulsaraswat.OrderService.model.OrderRequest;
import com.rahulsaraswat.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/placeOrder")
    ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {
            Long orderId = orderService.placeOrder(orderRequest);
            return new ResponseEntity<>(orderId, HttpStatus.OK);
    }
}
