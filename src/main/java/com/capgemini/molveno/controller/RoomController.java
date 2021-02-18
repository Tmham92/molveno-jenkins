package com.capgemini.molveno.controller;

import com.capgemini.molveno.model.Room;
import com.capgemini.molveno.model.formmodels.BookRoomsFilter;
import com.capgemini.molveno.model.formmodels.RoomFilter;
import com.capgemini.molveno.service.IRoomService;
import com.capgemini.molveno.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api")
public class RoomController {

    private final IRoomService roomService;

    private BookRoomsFilter filter;
    private RoomFilter detailsFilter;

    @Autowired
    public RoomController(IRoomService roomService) {
        this.roomService = roomService;
        filter = new BookRoomsFilter();
        filter.setArrivalDate(LocalDate.now());
        filter.setDepartureDate(LocalDate.now().plusDays(1));
        filter.setGuests(2);
        detailsFilter = new RoomFilter();
    }

    @GetMapping({ "/rooms", "/rooms/list" })
    private String getRooms(Model model) {
        if (model.containsAttribute("filter"))
            this.filter = (BookRoomsFilter) model.getAttribute("filter");

        model.addAttribute("rooms", roomService.filter(filter, detailsFilter));
        BigDecimal price = roomService.getPageForProperty("price", 1).iterator().next().getPrice();
        model.addAttribute("max_price", price);
        if (detailsFilter.getPriceRange() == null)
            detailsFilter.setPriceRange("0," + price);
        model.addAttribute("range", "[" + detailsFilter.getPriceRange() + "]");
        model.addAttribute("filter", filter);
        model.addAttribute("detailsFilter", detailsFilter);
        return "api/rooms/list";
    }

    @PostMapping("/rooms/filter")
    private String filterRooms(@ModelAttribute BookRoomsFilter filter) {
        this.filter = filter;
        return "redirect:/api/rooms/list";
    }

    @PostMapping("/rooms/filterDetails")
    private String filterDetails(@ModelAttribute RoomFilter filter) {
        this.detailsFilter = filter;
        return "redirect:/api/rooms/list";
    }

    @PostMapping("/employee/rooms")
    private String goToAddNewRoom() {
        return "redirect:rooms/add";
    }

    @GetMapping("/employee/rooms/add")
    private String getAddRooms(Model model) {
        model.addAttribute("newRoom", new Room());
        return "api/rooms/add";
    }

    @PostMapping("/employee/rooms/add")
    private String submitAddRoomForm(@ModelAttribute Room room, boolean isForDisabled, boolean isSmokingAllowed, Model model) {
        model.addAttribute("room", room);
        room.setSmokingAllowed(isSmokingAllowed);
        room.setForDisabled(isForDisabled);
        roomService.saveRoom(room);
        return "redirect:list";
    }

    private void setBooleanAttributed(Model model) {
        boolean isSmokingAllowed = false;
        model.addAttribute("isSmokingAllowed", isSmokingAllowed);

        boolean isForDisabled = false;
        model.addAttribute("isForDisabled", isForDisabled);

        boolean isPenthouse = false;
        model.addAttribute("isPenthouse", isPenthouse);

        int numberOfGuests = 0;
        model.addAttribute("numberOfGuests", numberOfGuests);
    }

    @RequestMapping(value = "/room/book", method =  RequestMethod.POST)
    public String postReservation(@ModelAttribute("id") Long id, Model model, RedirectAttributes redirectAttrs){
        redirectAttrs.addFlashAttribute("id", id);
        redirectAttrs.addFlashAttribute("roomsFilter", filter);
        return "redirect:/api/bookings/reserve";
    }

}
