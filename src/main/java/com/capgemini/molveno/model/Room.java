package com.capgemini.molveno.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int floor;
    private RoomType type;
    private boolean smokingAllowed;
    private boolean forDisabled;
    private int maxAdults;
    private int maxChildren;

    private BigDecimal price;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Booking> bookings;

    public Room(int floor, RoomType type, boolean smokingAllowed, boolean forDisabled, int maxAdults, int maxChildren, BigDecimal price) {
        this.floor = floor;
        this.type = type;
        this.smokingAllowed = smokingAllowed;
        this.forDisabled = forDisabled;
        this.maxAdults = maxAdults;
        this.maxChildren = maxChildren;
        this.price = price;
    }

    public Room() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public boolean isSmokingAllowed() {
        return smokingAllowed;
    }

    public void setSmokingAllowed(boolean smokingAllowed) {
        this.smokingAllowed = smokingAllowed;
    }

    public boolean isForDisabled() {
        return forDisabled;
    }

    public void setForDisabled(boolean forDisabled) {
        this.forDisabled = forDisabled;
    }

    public int getMaxAdults() {
        return maxAdults;
    }

    public void setMaxAdults(int maxAdults) {
        this.maxAdults = maxAdults;
    }

    public int getMaxChildren() {
        return maxChildren;
    }

    public void setMaxChildren(int maxChildren) {
        this.maxChildren = maxChildren;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", floor=" + floor +
                ", type=" + type +
                ", smokingAllowed=" + smokingAllowed +
                ", forDisabled=" + forDisabled +
                ", maxAdults=" + maxAdults +
                ", maxChildren=" + maxChildren +
                ", price=" + price +
                ", bookings=" + bookings +
                '}';
    }
}
