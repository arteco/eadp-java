package com.arteco.eadp.java.hotelrural.common.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class CancellationRequest {
    @NotNull
    private Long bookingId;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}
