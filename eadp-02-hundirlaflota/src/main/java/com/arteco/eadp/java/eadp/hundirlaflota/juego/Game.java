package com.arteco.eadp.java.eadp.hundirlaflota.juego;


import com.arteco.eadp.java.eadp.hundirlaflota.accion.Action;
import com.arteco.eadp.java.eadp.hundirlaflota.accion.LaunchAction;
import com.arteco.eadp.java.eadp.hundirlaflota.accion.PrintAction;
import com.arteco.eadp.java.eadp.hundirlaflota.accion.StartAction;

import java.util.Arrays;
import java.util.List;

public class Game {

    List<Action> actions = Arrays.asList(
            new StartAction(),
            new PrintAction(),
            new LaunchAction()

    );

    public List<Action> getActions() {
        return actions;
    }
}
