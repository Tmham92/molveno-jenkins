package com.capgemini.molveno.model.formmodels;

import com.capgemini.molveno.model.RoomType;

public class RoomFilter {
    private RoomType roomType;
    private Boolean smokingAllowed;
    private boolean forDisabled;
    private int children;
    private String priceRange;

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Boolean getSmokingAllowed() {
        return smokingAllowed;
    }

    public void setSmokingAllowed(Boolean smokingAllowed) {
        this.smokingAllowed = smokingAllowed;
    }

    public boolean isForDisabled() {
        return forDisabled;
    }

    public void setForDisabled(boolean forDisabled) {
        this.forDisabled = forDisabled;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }
}
