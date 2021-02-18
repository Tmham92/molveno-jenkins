package com.capgemini.molveno.model.formmodels;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public class BookRoomsFilter {

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate arrivalDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate departureDate;

    @NotNull @Min(1) @Max(100)
    private int guests;

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    @Override
    public String toString() {
        return "BookRoomsFilter{" +
                "arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", guests=" + guests +
                '}';
    }
}
