package com.capgemini.molveno.model.user;

import com.capgemini.molveno.model.Booking;

import javax.persistence.*;

@Entity
@Table(name="non_booking_guest")
public class NotBookingGuest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Booking booking;
    private String name;

    public NotBookingGuest(){}

    public NotBookingGuest(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
