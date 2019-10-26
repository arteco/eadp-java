package com.arteco.eadp.java.hotelrural.common.dto;

import com.arteco.eadp.java.hotelrural.common.dto.base.Dated;

import java.time.LocalDate;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class BookingListRequest implements Dated {
    private LocalDate from;
    private LocalDate to;

    @Override
    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    @Override
    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }
}
