package com.Rahulsaraswat.PaymentService.service;

import com.Rahulsaraswat.PaymentService.model.PaymentRequest;
import com.Rahulsaraswat.PaymentService.model.PaymentResponse;

public interface PaymentService {
    long makePayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetails(long orderId);
}
