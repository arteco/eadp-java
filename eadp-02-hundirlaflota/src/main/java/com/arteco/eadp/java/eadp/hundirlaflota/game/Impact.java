package com.arteco.eadp.java.eadp.hundirlaflota.game;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class Impact {
    private final Pos pos;
    private final ImpactType type;

    public Impact(Pos pos, ImpactType type) {
        this.pos = pos;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Impact{" +
                "pos=" + pos +
                ", type=" + type +
                '}';
    }
}
