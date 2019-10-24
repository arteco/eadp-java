package com.arteco.eadp.java.eadp.hundirlaflota.action;

import com.arteco.eadp.java.eadp.hundirlaflota.game.Game;

import java.util.List;

/**
 * Created by rarnau on 23/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class ExitAction implements Action {

    public static final String EXIT = "exit";

    @Override
    public String run(Game game, List<Object> arguments) {
        game.end();
        return "Terminando programa...";

    }

    @Override
    public String getDescription() {
        return "Termina la ejecución del programa";
    }

    @Override
    public String getName() {
        return EXIT;
    }
}
