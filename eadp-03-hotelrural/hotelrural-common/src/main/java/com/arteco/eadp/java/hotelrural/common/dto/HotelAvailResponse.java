package com.arteco.eadp.java.hotelrural.common.dto;

import com.arteco.eadp.java.hotelrural.common.dto.inner.RoomAvail;

import java.util.List;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class HotelAvailResponse {
    private List<RoomAvail> availability;

    public List<RoomAvail> getAvailability() {
        return availability;
    }

    public void setAvailability(List<RoomAvail> availability) {
        this.availability = availability;
    }
}
