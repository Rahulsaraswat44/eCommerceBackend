package com.Rahulsaraswat.PaymentService.controller;

import com.Rahulsaraswat.PaymentService.model.PaymentRequest;
import com.Rahulsaraswat.PaymentService.model.PaymentResponse;
import com.Rahulsaraswat.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/makePayment")
    ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest) {
        long paymentId = paymentService.makePayment(paymentRequest);
        return new ResponseEntity<>(paymentId, HttpStatus.CREATED);
    }

    @GetMapping("/order/{orderId}")
    ResponseEntity<PaymentResponse> getPaymentDetails(@PathVariable long orderId) {
        PaymentResponse paymentResponse = paymentService.getPaymentDetails(orderId);
        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }
}
