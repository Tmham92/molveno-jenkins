package com.capgemini.molveno.service;

import com.capgemini.molveno.controller.RoomsReservation;
import com.capgemini.molveno.model.Booking;
import com.capgemini.molveno.model.Room;
import com.capgemini.molveno.model.formmodels.BookRoomsFilter;
import com.capgemini.molveno.model.formmodels.NotBookingGuestForm;
import com.capgemini.molveno.model.user.Guest;

import java.time.LocalDateTime;

public interface IBookingService {
    void save(Booking booking);

    long count();

    Iterable<Booking> findAll();
  
    void createBooking(Room room, Guest g, RoomsReservation reservation, BookRoomsFilter filter);

}
