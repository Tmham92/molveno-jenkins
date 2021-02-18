package com.capgemini.molveno.controller;

import com.capgemini.molveno.model.Booking;
import com.capgemini.molveno.model.Room;
import com.capgemini.molveno.model.formmodels.BookRoomsFilter;
import com.capgemini.molveno.model.formmodels.NotBookingGuestForm;
import com.capgemini.molveno.model.user.Guest;
import com.capgemini.molveno.model.user.MyUserDetails;
import com.capgemini.molveno.model.user.NotBookingGuest;
import com.capgemini.molveno.model.user.User;
import com.capgemini.molveno.service.BookingService;
import com.capgemini.molveno.service.RoomService;
import com.capgemini.molveno.service.UserService;
import org.apache.tomcat.jni.Local;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final RoomService roomService;
    private final UserService userService;
    private BookRoomsFilter filter;
    private RoomsReservation reservation;
    private Long roomId;
    private List<Room> allRooms;
    private Guest guest;

    @Autowired
    public BookingController(BookingService bookingService, RoomService roomService, UserService userService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
        this.filter = new BookRoomsFilter();
        this.allRooms = new ArrayList<>();
        this.userService = userService;
    }

    @GetMapping("/reserve")
    public String getReservation(@ModelAttribute("id") Long id,
                                       @ModelAttribute("roomsFilter") BookRoomsFilter filter,
                                       Model model, Authentication auth){
        User user = this.userService.getUserFromEmail(auth.getName());
        this.guest = (Guest) user;

        if (allRooms.size() <= 0)
            allRooms = roomService.findAll();

        model.addAttribute("rooms", allRooms);

        this.filter = filter;
        model.addAttribute("roomsFilter", this.filter);

        model.addAttribute("guests", new NotBookingGuestForm());
        model.addAttribute("user", user);


        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        model.addAttribute("birthDate", simpleDateFormat.format(user.getDateOfBirth()));

        ZoneId defaultZoneId = ZoneId.systemDefault();
        model.addAttribute("arrivalDate", simpleDateFormat.format(Date.from(filter.getArrivalDate().atStartOfDay(defaultZoneId).toInstant())));
        model.addAttribute("departureDate", simpleDateFormat.format(Date.from(filter.getDepartureDate().atStartOfDay(defaultZoneId).toInstant())));

        if (this.reservation == null) {
            RoomsReservation reservation = new RoomsReservation();
            reservation.setNotBookingGuests(Collections.singletonList(new NotBookingGuest("Erik")));
            this.reservation = reservation;
        }
        this.roomId = id;
        model.addAttribute("reservation", this.reservation);
        return "/api/bookings/reserve";
    }

    @RequestMapping(value = "/submit", params = {"addRoom"})
    public String addRoom(@ModelAttribute RoomsReservation reservation, Model model, RedirectAttributes redirectAttributes) {
        this.reservation = reservation;
        redirectAttributes.addFlashAttribute("roomsFilter", this.filter);
        redirectAttributes.addFlashAttribute("id", this.roomId);
        return "redirect:/api/bookings/reserve";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitReservation(@ModelAttribute NotBookingGuestForm guests,
                                    Authentication authentication, Model model){
        this.reservation.setNotBookingGuests(guests.getNotBookingGuestList());
        bookingService.createBooking(roomService.findRoomById(this.roomId).orElse(null), guest, reservation, this.filter);
        return "redirect:/api/rooms";
    }


}

