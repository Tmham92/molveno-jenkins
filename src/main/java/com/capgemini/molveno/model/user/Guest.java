package com.capgemini.molveno.model.user;

import com.capgemini.molveno.model.Booking;
import com.capgemini.molveno.model.details.EnterpriseDetails;
import com.capgemini.molveno.model.details.PersonDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Guest extends User{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private PersonDetails details;

    // One Guest can have multiple Bookings
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="booking_id")
    private Set<Booking> bookings = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<EnterpriseDetails> enterpriseDetails = new HashSet<>();

    public Set<EnterpriseDetails> getEnterpriseDetails() {
        return enterpriseDetails;
    }

    public void setEnterpriseDetails(Set<EnterpriseDetails> enterpriseDetails) {
        this.enterpriseDetails = enterpriseDetails;
    }

    public PersonDetails getDetails() {
        return details;
    }

    public void setDetails(PersonDetails details) {
        this.details = details;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String getRole() {
        return "Guest";
    }

    @Override
    public String toString() {
        return "Guest{" +
                ", details=" + details +
                ", bookings=" + bookings +
                '}';
    }
}
