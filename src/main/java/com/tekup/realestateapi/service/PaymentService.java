package com.tekup.realestateapi.service;


import com.tekup.realestateapi.models.Payment;
import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayments();
    Payment getPaymentById(Long id);
    Payment savePayment(Payment payment);
}
