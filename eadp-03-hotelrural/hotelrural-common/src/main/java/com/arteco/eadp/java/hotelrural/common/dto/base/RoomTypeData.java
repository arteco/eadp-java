package com.arteco.eadp.java.hotelrural.common.dto.base;

import com.arteco.eadp.java.hotelrural.common.dto.inner.RoomType;

public class RoomTypeData extends Dated {
    private RoomType roomType;

    public RoomTypeData() {

    }


    public RoomTypeData(RoomTypeData data) {
        super(data);
        this.roomType = data.getRoomType();

    }


    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
