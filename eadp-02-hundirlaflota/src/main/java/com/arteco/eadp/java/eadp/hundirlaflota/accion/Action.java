package com.arteco.eadp.java.eadp.hundirlaflota.accion;

import com.arteco.eadp.java.eadp.hundirlaflota.juego.Game;

import java.util.List;

public interface Action {
    void run(Game game, List<Object> arguments);

    String getDescription();

    String getName();
}
