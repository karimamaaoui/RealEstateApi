package com.tekup.realestateapi.service;

import com.tekup.realestateapi.models.Booking;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {
    ResponseEntity<List<Booking>> getAllBookings();

    ResponseEntity<Booking> getBookingById(Long id);

    ResponseEntity<?> saveBooking(Booking booking);

    ResponseEntity<?> deleteBooking(Long id);

    ResponseEntity<Booking> updateBooking(Long id, Booking updatedBooking);
}
