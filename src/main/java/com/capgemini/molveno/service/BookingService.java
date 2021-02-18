package com.capgemini.molveno.service;

import com.capgemini.molveno.controller.RoomsReservation;
import com.capgemini.molveno.model.Booking;
import com.capgemini.molveno.model.Room;
import com.capgemini.molveno.model.formmodels.BookRoomsFilter;
import com.capgemini.molveno.model.formmodels.NotBookingGuestForm;
import com.capgemini.molveno.model.user.Guest;
import com.capgemini.molveno.model.user.NotBookingGuest;
import com.capgemini.molveno.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class BookingService implements IBookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public long count() {
        return bookingRepository.count();
    }

    @Override
    public Iterable<Booking> findAll() {
        return bookingRepository.findAll();
    }

   public void createBooking(Room room, Guest g, RoomsReservation reservation, BookRoomsFilter filter) {
        LocalDateTime dateOfCreation = LocalDateTime.now();
        Booking b = new Booking();
        b.setId(count()+1);
        b.setDateOfCreation(dateOfCreation);
        b.setLastUpdated(dateOfCreation);
        b.setBeginDate(filter.getArrivalDate());
        b.setEndDate(filter.getDepartureDate());
        b.setNumberOfGuests(filter.getGuests());
        b.addRoomsBooked(room);
        b.setGuest(g);
        b.setNonBookingGuests(new HashSet<>(reservation.getNotBookingGuests()));
        save(b);
    }
}
