package com.rahulsaraswat.OrderService.external.client;

import com.rahulsaraswat.OrderService.external.request.PaymentRequest;
import com.rahulsaraswat.OrderService.external.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentService {
    @PostMapping("/makePayment")
    ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);

    @GetMapping("/order/{orderId}")
    ResponseEntity<PaymentResponse> getPaymentDetails(@PathVariable long orderId);

}
