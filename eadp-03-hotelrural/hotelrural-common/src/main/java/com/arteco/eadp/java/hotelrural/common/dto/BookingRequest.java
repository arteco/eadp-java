package com.arteco.eadp.java.hotelrural.common.dto;

import com.arteco.eadp.java.hotelrural.common.dto.base.MealPlanData;
import com.arteco.eadp.java.hotelrural.common.dto.inner.MealPlan;
import com.arteco.eadp.java.hotelrural.common.dto.inner.RoomType;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class BookingRequest implements MealPlanData {
    @NotNull
    private String customerId;
    @NotNull
    private LocalDate from;
    @NotNull
    private LocalDate to;
    @NotNull
    private RoomType roomType;
    @NotNull
    private MealPlan mealPlan;

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

}
