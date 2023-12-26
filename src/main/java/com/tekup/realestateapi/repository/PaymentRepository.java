package com.tekup.realestateapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekup.realestateapi.models.Payment;

@Repository

public interface PaymentRepository extends JpaRepository<Payment, Long> {


}
