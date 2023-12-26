package com.tekup.realestateapi.serviceImp;


import com.tekup.realestateapi.models.Booking;
import com.tekup.realestateapi.models.Payment;
import com.tekup.realestateapi.repository.BookingRepository;
import com.tekup.realestateapi.repository.PaymentRepository;
import com.tekup.realestateapi.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private BookingRepository bookingRepository;
    
     
    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(Long id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        return optionalPayment.orElse(null);
    }

    @Override
    public Payment savePayment(Payment inputPayment) {
        Booking booking = bookingRepository.findById(inputPayment.getBooking().getId()).orElse(null);

        if (booking != null) {
            Payment payment = new Payment();
            payment.setDateDePaiement(inputPayment.getDateDePaiement());
            payment.setPrice(inputPayment.getPrice());
            payment.setTypePayment(inputPayment.getTypePayment());
            payment.setBooking(booking); // Associate the existing Booking entity

            return paymentRepository.save(payment);
        }

        return null;
    }
}
