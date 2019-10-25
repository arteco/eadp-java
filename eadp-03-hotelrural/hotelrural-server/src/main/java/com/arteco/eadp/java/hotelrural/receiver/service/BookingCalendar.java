package com.arteco.eadp.java.hotelrural.receiver.service;


import com.arteco.eadp.java.hotelrural.common.dto.base.Dated;
import com.arteco.eadp.java.hotelrural.common.dto.base.RoomTypeData;
import com.arteco.eadp.java.hotelrural.common.dto.inner.RoomType;
import com.arteco.eadp.java.hotelrural.receiver.persistence.model.Booking;
import com.arteco.eadp.java.hotelrural.receiver.persistence.model.Room;

import javax.annotation.Nullable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
class BookingCalendar {

    private Map<LocalDate, Map<Long, RoomDay>> calendar = new HashMap<>();
    private final List<Room> availableRooms;

    BookingCalendar(List<Room> availableRooms) {
        this.availableRooms = Collections.unmodifiableList(availableRooms);
    }

    List<Room> availability(RoomTypeData request) {
        Dated dated = new Dated(request.getFrom(), request.getTo());
        getOrCreateRoomDays(dated, null);

        Stream<LocalDate> stream = dated.getFrom().datesUntil(dated.getTo());
        final Integer days = Period.between(dated.getFrom(),dated.getTo()).getDays();

        Map<Long,Integer> roomAvail = new HashMap<>();
        stream.forEach(actual->{
            Map<Long, RoomDay> dayMap = calendar.get(actual);
            for(Map.Entry<Long,RoomDay> entry : dayMap.entrySet()){
                Integer count = roomAvail.putIfAbsent(entry.getKey(), 0);
                count++;
                roomAvail.put(entry.getKey(),count);
            }
         });

        Set<Long> availRoomIds = roomAvail.entrySet().stream()
                .filter((entry) -> days.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        return availableRooms
                .stream()
                .filter(room -> availRoomIds.contains(room.getId()))
                .collect(Collectors.toList());
    }

    void lock(Booking booking) {
        Dated dated = new Dated(booking.getFrom(), booking.getTo());
        getOrCreateRoomDays(dated, booking.getRoom().getId())
                .forEach(roomDay -> roomDay.bookingId = booking.getId());
    }


    private Collection<RoomDay> getOrCreateRoomDays(Dated dated, @Nullable Long roomId) {
        List<RoomDay> result = new ArrayList<>();
        Stream<LocalDate> stream = dated.getFrom().datesUntil(dated.getTo());
        stream.forEach (actual -> {
            Map<Long, RoomDay> map = calendar.computeIfAbsent(actual, this::initDay);
            if (roomId != null) {
                RoomDay roomDay = map.get(roomId);
                result.add(roomDay);
            } else {
                result.addAll(map.values());
            }
        });
        return Collections.unmodifiableList(result);
    }

    private Map<Long, RoomDay> initDay( LocalDate localDate) {
        Map<Long, RoomDay> result = new HashMap<>();
        for (Room room : availableRooms) {
            result.put(room.getId(), new RoomDay(localDate, room.getId(), room.getRoomType()));
        }
        return result;
    }


    void unlock(Booking booking) {
        Dated dated = new Dated(booking.getFrom(), booking.getTo());
        getOrCreateRoomDays(dated, booking.getRoom().getId())
                .forEach(roomDay -> roomDay.bookingId = null);
    }

    private class RoomDay {
        final LocalDate date;
        final Long roomId;
        final RoomType roomType;
        Long bookingId;


        private RoomDay(LocalDate date, Long roomId, RoomType roomType) {
            this.date = date;
            this.roomId = roomId;
            this.roomType = roomType;
        }
    }
}
