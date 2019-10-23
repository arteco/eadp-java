package com.arteco.eadp.java.eadp.hundirlaflota.accion;

import com.arteco.eadp.java.eadp.hundirlaflota.juego.Game;

import java.util.List;

/**
 * Created by rarnau on 23/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class LaunchAction implements Action {
    @Override
    public void run(Game game, List<Object> arguments) {
        System.out.println("Running launch!");
    }

    @Override
    public String getDescription() {
        return "Lanza un misil sobre el tablero del oponente. Requiere indicar posición X e Y";
    }

    @Override
    public String getName() {
        return "launch";
    }
}
