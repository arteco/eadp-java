package com.arteco.eadp.java.hotelrural.common.dto;

import com.arteco.eadp.java.hotelrural.common.dto.base.MealPlanData;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class BookingRequest extends MealPlanData {
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
