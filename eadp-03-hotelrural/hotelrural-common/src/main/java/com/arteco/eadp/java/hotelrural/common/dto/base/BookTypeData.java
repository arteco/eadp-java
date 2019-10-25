package com.arteco.eadp.java.hotelrural.common.dto.base;

import com.arteco.eadp.java.hotelrural.common.dto.inner.MealPlan;

public class BookTypeData extends RoomTypeData {
    private MealPlan mealPlan;


    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }
}
