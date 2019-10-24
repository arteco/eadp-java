package com.arteco.eadp.java.eadp.hundirlaflota.game;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class BoardCell {
    private Boat boat;
    private Integer idx;
    private boolean impact;

    public boolean isBusy() {
        return boat != null;
    }

    public Boat getBoat() {
        return boat;
    }

    public Integer getIdx() {
        return idx;
    }

    public void alloc(Boat boat, int idx) {
        this.boat = boat;
        this.idx = idx;
        this.impact = false;
    }


    public ImpactType evaluateImpact() {
        this.impact = true;
        ImpactType result = ImpactType.WATER;
        if (boat != null) {
            result = boat.impact(idx);
        }
        return result;
    }

    public boolean isImpact() {
        return impact;
    }
}
