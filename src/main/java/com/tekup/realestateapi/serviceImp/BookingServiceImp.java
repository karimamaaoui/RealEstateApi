package com.tekup.realestateapi.serviceImp;

import com.tekup.realestateapi.models.Booking;
import com.tekup.realestateapi.models.EState;
import com.tekup.realestateapi.models.EStates;
import com.tekup.realestateapi.models.RealEstate;
import com.tekup.realestateapi.repository.BookingRepository;
import com.tekup.realestateapi.repository.RealEstateRepository;
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

    @Autowired
    private RealEstateRepository estateRepository;
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
    public ResponseEntity<?> saveBooking(Booking booking) {
        try {
            Optional<Booking> existingBooking = bookingRepository.findByRealEstate_Id(booking.getRealEstate().getId());
            if (existingBooking.isPresent()) {
                return new ResponseEntity<>("A booking already exists for the given real estate", HttpStatus.CONFLICT);
            }

            Booking savedBooking = bookingRepository.save(booking);

            RealEstate realEstate = savedBooking.getRealEstate();
            if (realEstate != null) {
                realEstate = estateRepository.getById(realEstate.getId()); 
                realEstate.setState(EState.UNAVAILABLE);
                if (EStates.RENT.equals(realEstate.getStates())) {
                    savedBooking.setNumberOfDaysOrMonths(booking.getNumberOfDaysOrMonths());
                } else {
                    savedBooking.setNumberOfDaysOrMonths(0);
                }

                estateRepository.save(realEstate);
            }

            return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    public ResponseEntity<?> deleteBooking(Long id) {
        try {
            Optional<Booking> bookingOptional = bookingRepository.findById(id);
            if (bookingOptional.isPresent()) {
                Booking booking = bookingOptional.get();
                RealEstate realEstate = booking.getRealEstate();
                   if (realEstate != null) {
                    realEstate.setState(EState.AVAILABLE);
                    estateRepository.save(realEstate);
                }

                bookingRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
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

                if (updatedBooking.getNumberOfDaysOrMonths() != null) {
                    existingBooking.setNumberOfDaysOrMonths(updatedBooking.getNumberOfDaysOrMonths());
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
