package com.arteco.eadp.java.hotelrural.common.dto;

import com.arteco.eadp.java.hotelrural.common.dto.base.BookData;

import java.util.List;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class BookingsResponse {
    private List<BookData> bookings;

    public List<BookData> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookData> bookings) {
        this.bookings = bookings;
    }
}
