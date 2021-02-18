package com.capgemini.molveno.model.formmodels;

import com.capgemini.molveno.model.user.NotBookingGuest;

import java.util.List;

public class NotBookingGuestForm {

    private List<NotBookingGuest> notBookingGuestList;

    public NotBookingGuestForm(){}

    public NotBookingGuestForm(List<NotBookingGuest> notBookingGuestList) {
        this.notBookingGuestList = notBookingGuestList;
    }

    public List<NotBookingGuest> getNotBookingGuestList() {
        return notBookingGuestList;
    }

    public void setNotBookingGuestList(List<NotBookingGuest> notBookingGuestList) {
        this.notBookingGuestList = notBookingGuestList;
    }

    @Override
    public String toString() {
        return "NotBookingGuestForm{" +
                "notBookingGuestList=" + notBookingGuestList +
                '}';
    }
}
