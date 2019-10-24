package com.arteco.eadp.java.eadp.hundirlaflota.game;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class Boat {
    private final int id;
    private final String name;
    private final int size;
    private final boolean[] impacted;

    public Boat(Fleet fleet, int id) {
        this(id, fleet.getName(), fleet.getSize());
    }


    public Boat(int id, String name, int size) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.impacted = new boolean[size];
    }


    public int getId() {
        return id;
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
}
