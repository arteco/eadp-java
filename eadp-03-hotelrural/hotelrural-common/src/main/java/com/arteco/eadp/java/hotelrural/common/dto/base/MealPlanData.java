package com.arteco.eadp.java.hotelrural.common.dto.base;

import com.arteco.eadp.java.hotelrural.common.dto.inner.MealPlan;

public class MealPlanData extends RoomTypeData {
    private MealPlan mealPlan;

    public MealPlanData() {

    }

    public MealPlanData(MealPlanData data) {
        super(data);
        this.mealPlan = data.mealPlan;
    }


    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }
}
