package com.Rahulsaraswat.PaymentService.service;

import com.Rahulsaraswat.PaymentService.entity.TransactionDetails;
import com.Rahulsaraswat.PaymentService.model.PaymentMode;
import com.Rahulsaraswat.PaymentService.model.PaymentRequest;
import com.Rahulsaraswat.PaymentService.repository.PaymentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public long makePayment(PaymentRequest paymentRequest) {

        log.info("making payment for orderID: {}", paymentRequest.getOrderId());
        TransactionDetails transactionDetail =
                TransactionDetails.builder()
                        .orderId(paymentRequest.getOrderId())
                        .paymentMode(paymentRequest.getPaymentMode())
                        .amount(paymentRequest.getAmount())
                        .paymentStatus("SUCCESSFUL")
                        .paymentDate(Instant.now())
                        .referenceNumber("123456")
                        .build();

        paymentRepository.save(transactionDetail);
        log.info("Payment successful for orderId: {}" , paymentRequest.getOrderId());

        return transactionDetail.getId();
    }
}
