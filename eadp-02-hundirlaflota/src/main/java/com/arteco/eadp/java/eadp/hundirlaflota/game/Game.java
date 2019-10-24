package com.arteco.eadp.java.eadp.hundirlaflota.game;


import com.arteco.eadp.java.eadp.hundirlaflota.action.*;

import java.util.Arrays;
import java.util.List;

import static com.arteco.eadp.java.eadp.hundirlaflota.action.Action.EOL;

public class Game {

    private final int N;
    private final List<Fleet> fleets;
    private final Board boardUSR;
    private final Board boardCPU;

    private final List<Action> actions = Arrays.asList(
            new StartAction(),
            new PrintAction(),
            new LaunchAction(),
            new ExitAction()

    );
    private boolean end = false;

    public Game() {
        this(10, Arrays.asList(
                new Fleet("portaaviones", 5, 1),
                new Fleet("crucero", 4, 2),
                new Fleet("destructor", 3, 3),
                new Fleet("fragata", 2, 3),
                new Fleet("submarino", 1, 4)));
    }

    public Game(int N, List<Fleet> fleets) {
        this.fleets = fleets;
        this.N = N;
        this.boardCPU = new Board(N);
        this.boardUSR = new Board(N);
    }

    public List<Action> getActions() {
        return actions;
    }

    public List<Fleet> getFleets() {
        return fleets;
    }

    public int getN() {
        return N;
    }

    public Board getBoardUSR() {
        return boardUSR;
    }

    public Board getBoardCPU() {
        return boardCPU;
    }

    public String print() {
        StringBuilder stb = new StringBuilder();
        stb.append("Usuario").append(EOL);
        stb.append(this.getBoardUSR().print()).append(EOL);
        stb.append("CPU").append(EOL);
        stb.append(this.getBoardCPU().print()).append(EOL);
        return stb.toString();
    }

    public void terminate() {
        this.end = true;
    }

    public boolean isEnd() {
        return end;
    }
}
