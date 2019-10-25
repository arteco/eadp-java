package com.arteco.eadp.java.hotelrural.receiver.persistence.model;

import com.arteco.eadp.java.hotelrural.common.dto.inner.MealPlan;
import com.arteco.eadp.java.hotelrural.common.dto.inner.RoomType;

import javax.persistence.*;

@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Enumerated(EnumType.STRING)
    private MealPlan mealPlan;

    private Float dayPrice;

    public Price(RoomType roomType, MealPlan mealPlan, Float dayPrice) {
        this.roomType = roomType;
        this.mealPlan = mealPlan;
        this.dayPrice = dayPrice;
    }

    public Price() {

    }

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

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }

    public Float getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(Float dayPrice) {
        this.dayPrice = dayPrice;
    }
}
