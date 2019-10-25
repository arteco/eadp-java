package com.arteco.eadp.java.hotelrural.common.dto.base;

public class BookData extends PriceData {
    private Long roomId;
    private String customerId;
    private Long bookingId;

    public BookData() {
    }

    public BookData(BookData data) {
        super(data);
        this.roomId = data.roomId;
        this.customerId = data.customerId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}

