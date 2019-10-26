package com.arteco.eadp.java.hotelrural.common.dto;

import java.util.List;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class BookingListResponse {
    private List<BookingResponse> bookings;

    public List<BookingResponse> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingResponse> bookings) {
        this.bookings = bookings;
    }
}
