package com.arteco.eadp.java.eadp.hundirlaflota.action;

import com.arteco.eadp.java.eadp.hundirlaflota.game.Game;

import java.util.List;

/**
 * Created by rarnau on 23/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class PrintAction implements Action {
    @Override
    public String run(Game game, List<Object> arguments) {
        return game.print();
    }

    @Override
    public String getDescription() {
        return "Imprime el estado de la partida";
    }

    @Override
    public String getName() {
        return "print";
    }
}
