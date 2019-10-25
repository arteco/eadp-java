package com.arteco.eadp.java.hotelrural.receiver.persistence.model;

import com.arteco.eadp.java.hotelrural.common.dto.RoomType;
import com.arteco.eadp.java.hotelrural.common.dto.RoomType;

import javax.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
