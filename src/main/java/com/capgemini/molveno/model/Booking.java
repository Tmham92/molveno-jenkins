package com.capgemini.molveno.model;

import com.capgemini.molveno.model.user.Guest;
import com.capgemini.molveno.model.user.NotBookingGuest;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime dateOfCreation;
    private LocalDateTime lastUpdated;
    private LocalDate beginDate;
    private LocalDate endDate;
    private int numberOfGuests;
    private String bookedBy;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Room> roomsBooked = new HashSet<Room>();

    // One booking can have multiple non booking guests
    @OneToMany(cascade = CascadeType.ALL)
    private Set<NotBookingGuest> nonBookingGuests;

    // One Guest can have multiple bookings.
    @ManyToOne(cascade = CascadeType.ALL)
    private Guest guest;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public Set<Room> getRoomsBooked() {
        return new HashSet<Room>(roomsBooked);
    }

    public void addRoomsBooked(Room room) {
        if (roomsBooked.contains(room)){
            return;
        }
        roomsBooked.add(room);
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    public void setRoomsBooked(Set<Room> roomsBooked) {
        this.roomsBooked = roomsBooked;
    }

    public Set<NotBookingGuest> getNonBookingGuests() {
        return nonBookingGuests;
    }

    public void setNonBookingGuests(Set<NotBookingGuest> nonBookingGuests) {
        this.nonBookingGuests = nonBookingGuests;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }
}
