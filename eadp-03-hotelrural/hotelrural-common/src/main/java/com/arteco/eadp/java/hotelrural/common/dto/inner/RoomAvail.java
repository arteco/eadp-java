package com.arteco.eadp.java.hotelrural.common.dto.inner;

import java.util.List;

public class RoomAvail {
    private RoomType roomType;
    private List<RoomPrice> prices;

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public List<RoomPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<RoomPrice> prices) {
        this.prices = prices;
    }
}
