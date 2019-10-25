package com.arteco.eadp.java.hotelrural.receiver.persistence.model;

import com.arteco.eadp.java.hotelrural.common.dto.MealPlan;

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
}
