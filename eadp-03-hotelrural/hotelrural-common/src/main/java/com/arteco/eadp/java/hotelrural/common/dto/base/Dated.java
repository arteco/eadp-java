package com.arteco.eadp.java.hotelrural.common.dto.base;

import java.time.LocalDate;

public class Dated {
    private LocalDate from;
    private LocalDate to;

    public Dated() {

    }

    public Dated(Dated data) {
        this(data.getFrom(),data.getTo());
    }

    public Dated(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }
}
