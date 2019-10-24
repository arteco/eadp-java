package com.arteco.eadp.java.eadp.hundirlaflota.game;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class Boat {
    private final String name;
    private final int size;
    private final boolean[] impacted;
    private final Pos pos;
    private final boolean horizontal;

    public Boat(Fleet fleet, Pos pos, boolean horizontal) {
        this(fleet.getName(), fleet.getSize(), pos, horizontal);
    }


    public Boat(String name, int size, Pos pos, boolean horizontal) {
        this.name = name;
        this.size = size;
        this.impacted = new boolean[size];
        this.pos = pos;
        this.horizontal = horizontal;
    }


    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public ImpactType impact(Integer idx) {
        updateStatus(idx);
        boolean alive = isAlive();
        return alive ? ImpactType.TOUCHED : ImpactType.SUNKEN;
    }

    public boolean isAlive() {
        for (boolean b : this.impacted) {
            if (!b) {
                return true;
            }
        }
        return false;
    }

    private void updateStatus(Integer idx) {
        if (idx != null && idx >= 0 && idx < size) {
            this.impacted[idx] = true;
        }
    }

    public Pos getPos() {
        return pos;
    }

    public boolean[] getImpacted() {
        return impacted;
    }

    public boolean isHorizontal() {
        return horizontal;
    }
}
