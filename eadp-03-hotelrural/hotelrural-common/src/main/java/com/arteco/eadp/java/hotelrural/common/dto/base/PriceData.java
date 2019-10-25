package com.arteco.eadp.java.hotelrural.common.dto.base;

public class PriceData extends MealPlanData {
    private Float price;

    public PriceData() {

    }

    public PriceData(PriceData data) {
        super(data);
        this.price = data.price;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
