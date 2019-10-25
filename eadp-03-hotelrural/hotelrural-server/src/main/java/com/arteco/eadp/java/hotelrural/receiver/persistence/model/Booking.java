package com.arteco.eadp.java.hotelrural.receiver.persistence.model;

import com.arteco.eadp.java.hotelrural.common.dto.BookingRequest;
import com.arteco.eadp.java.hotelrural.common.dto.base.BookData;
import com.arteco.eadp.java.hotelrural.common.dto.inner.MealPlan;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
@Entity
public class Booking {
    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.DATE)

    private LocalDate from;

    @Temporal(TemporalType.DATE)
    private LocalDate to;

    @Enumerated(EnumType.STRING)
    private MealPlan mealPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    private Float price;

    private String customerId;

    public static Booking of(BookingRequest request, Room room, Float price) {
        Booking booking = new Booking();
        booking.setCustomerId(request.getCustomerId());
        booking.setFrom(request.getFrom());
        booking.setMealPlan(request.getMealPlan());
        booking.setTo(request.getTo());
        booking.setPrice(price);
        booking.setRoom(room);
        return booking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BookData toBookData() {
        BookData data = new BookData();
        return toBookData(data);
    }

    public BookData toBookData(BookData data) {
        data.setCustomerId(this.customerId);
        data.setFrom(this.from);
        data.setMealPlan(this.mealPlan);
        data.setRoomId(this.room.getId());
        data.setRoomType(this.room.getRoomType());
        data.setTo(this.to);
        data.setPrice(this.price);
        data.setBookingId(this.id);
        return data;
    }
}
