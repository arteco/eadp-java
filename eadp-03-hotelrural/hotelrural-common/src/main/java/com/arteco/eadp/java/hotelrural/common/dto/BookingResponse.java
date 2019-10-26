package com.arteco.eadp.java.hotelrural.common.dto;

import com.arteco.eadp.java.hotelrural.common.dto.base.BookData;
import com.arteco.eadp.java.hotelrural.common.dto.inner.MealPlan;
import com.arteco.eadp.java.hotelrural.common.dto.inner.RoomType;

import java.time.LocalDate;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class BookingResponse implements BookData {
    private String customerId;
    private LocalDate from;
    private LocalDate to;
    private RoomType roomType;
    private MealPlan mealPlan;
    private Float price;
    private Long roomId;
    private Long bookingId;

    @Override
    public String toString() {
        return "BookingResponse{" +
                "customerId='" + customerId + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", roomType=" + roomType +
                ", mealPlan=" + mealPlan +
                ", price=" + price +
                ", roomId=" + roomId +
                ", bookingId=" + bookingId +
                '}';
    }

    @Override
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

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

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }

    @Override
    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    @Override
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}
