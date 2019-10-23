package com.arteco.eadp.java.eadp.hundirlaflota.accion;

import com.arteco.eadp.java.eadp.hundirlaflota.juego.Game;

import java.util.List;

/**
 * Created by rarnau on 23/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class StartAction implements Action {
    @Override
    public void run(Game game, List<Object> arguments) {

    }

    @Override
    public String getDescription() {
        return "Comienza una nueva partida, borrando la partida actual";
    }

    @Override
    public String getName() {
        return "start";
    }
}
