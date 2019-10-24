package com.arteco.eadp.java.eadp.hundirlaflota.game;


import com.arteco.eadp.java.eadp.hundirlaflota.action.Action;
import com.arteco.eadp.java.eadp.hundirlaflota.action.LaunchAction;
import com.arteco.eadp.java.eadp.hundirlaflota.action.PrintAction;
import com.arteco.eadp.java.eadp.hundirlaflota.action.StartAction;

import java.util.Arrays;
import java.util.List;

public class Game {

    private final int N;
    private final List<Fleet> fleets;
    private final Board boardUSR;
    private final Board boardCPU;

    private final List<Action> actions = Arrays.asList(
            new StartAction(),
            new PrintAction(),
            new LaunchAction()

    );

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

    public void print() {
        System.out.println("Usuario");
        System.out.println(this.getBoardUSR().print());
        System.out.println("CPU");
        System.out.println(this.getBoardCPU().print());
    }
}
