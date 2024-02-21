package com.Rahulsaraswat.PaymentService.service;

import com.Rahulsaraswat.PaymentService.model.PaymentRequest;

public interface PaymentService {
    long makePayment(PaymentRequest paymentRequest);
}
