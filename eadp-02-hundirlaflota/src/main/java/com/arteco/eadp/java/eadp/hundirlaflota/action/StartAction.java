package com.arteco.eadp.java.eadp.hundirlaflota.action;

import com.arteco.eadp.java.eadp.hundirlaflota.game.Board;
import com.arteco.eadp.java.eadp.hundirlaflota.game.Fleet;
import com.arteco.eadp.java.eadp.hundirlaflota.game.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rarnau on 23/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class StartAction implements Action {

    @Override
    public String run(Game game, List<Object> arguments) {
        List<Fleet> fleet = game.getFleets();

        if (arguments != null && arguments.size() > 0) {
            fleet = new ArrayList<>();
            fleet.add(new Fleet("test", 2, 1));
        }

        Board board = game.getBoardUSR();
        board.clear();
        board.place(fleet);

        board = game.getBoardCPU();
        board.clear();
        board.place(fleet);

        return "Partida inicializada!";

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
