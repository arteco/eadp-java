package com.arteco.eadp.java.hotelrural.common.dto.inner;

public class RoomPrice {
    private Float dayPrice;
    private MealPlan mealPlan;

    public Float getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(Float dayPrice) {
        this.dayPrice = dayPrice;
    }

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }
}
