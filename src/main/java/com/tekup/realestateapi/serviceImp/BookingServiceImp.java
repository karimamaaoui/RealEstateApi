package com.tekup.realestateapi.serviceImp;

import com.tekup.realestateapi.models.Booking;
import com.tekup.realestateapi.repository.BookingRepository;
import com.tekup.realestateapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImp implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id)
                .map(booking -> new ResponseEntity<>(booking, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Booking> saveBooking(Booking booking) {
        try {
            // You can add additional validation or business logic here if needed
            Booking savedBooking = bookingRepository.save(booking);
            return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteBooking(Long id) {
        try {
            bookingRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Booking> updateBooking(Long id, Booking updatedBooking) {
        try {
            Optional<Booking> bookingOptional = bookingRepository.findById(id);
            if (bookingOptional.isPresent()) {
                Booking existingBooking = bookingOptional.get();

                if (updatedBooking.getUser() != null) {
                    existingBooking.setUser(updatedBooking.getUser());
                }

                if (updatedBooking.getRealEstate() != null) {
                    existingBooking.setRealEstate(updatedBooking.getRealEstate());
                }

                if (updatedBooking.getBookingDate() != null) {
                    existingBooking.setBookingDate(updatedBooking.getBookingDate());
                }

                if (updatedBooking.getStateReservation() != null) {
                    existingBooking.setStateReservation(updatedBooking.getStateReservation());
                }

                Booking updated = bookingRepository.save(existingBooking);
                return new ResponseEntity<>(updated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
}
