package com.tekup.realestateapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekup.realestateapi.models.Booking;

@Repository
public interface BookingRepository extends JpaRepository <Booking,Long>{

}
