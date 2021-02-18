package com.capgemini.molveno.controller;

import com.capgemini.molveno.model.Room;
import com.capgemini.molveno.model.user.NotBookingGuest;

import java.util.ArrayList;
import java.util.List;

public class RoomsReservation {
    private List<Room> selectedRooms;
    private List<NotBookingGuest> notBookingGuests;

    public RoomsReservation() {
        selectedRooms = new ArrayList<>();
        notBookingGuests = new ArrayList<>();
    }

    public List<Room> getSelectedRooms() {
        return selectedRooms;
    }

    public void setSelectedRooms(List<Room> selectedRooms) {
        this.selectedRooms = selectedRooms;
    }

    public List<NotBookingGuest> getNotBookingGuests() {
        return notBookingGuests;
    }

    public void setNotBookingGuests(List<NotBookingGuest> notBookingGuests) {
        this.notBookingGuests = notBookingGuests;
    }
}
