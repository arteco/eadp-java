package com.arteco.eadp.java.eadp.hundirlaflota.action;

import com.arteco.eadp.java.eadp.hundirlaflota.game.Game;
import com.arteco.eadp.java.eadp.hundirlaflota.game.Impact;
import com.arteco.eadp.java.eadp.hundirlaflota.game.Pos;

import java.util.List;

/**
 * Created by rarnau on 23/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class LaunchAction implements Action {
    @Override
    public void run(Game game, List<Object> arguments) {
        Impact impactUSR;
        Impact impactCPU;

        Pos pos = getUserLaunchPos(arguments);
        if (pos != null) {
            impactUSR = game.getBoardCPU().evaluateImpact(pos);
            System.out.println("User -> " + impactUSR);
        }

        pos = getCpuLaunchPos(game);
        impactCPU = game.getBoardUSR().evaluateImpact(pos);
        System.out.println("Cpu  -> " + impactCPU);


        boolean isUsrAlive = game.getBoardUSR().isAlive();
        boolean isCpuAlive = game.getBoardCPU().isAlive();

        if (!isUsrAlive || !isCpuAlive) {
            System.out.println("\n");
            if (isCpuAlive == isUsrAlive) {
                System.out.println("¡¡¡EMPATE!!!");
            } else if (isCpuAlive) {
                System.out.println("¡¡¡OPS, HA PERDIDO :( !!!");
            } else {
                System.out.println("¡¡¡ENHORABUENA, HA GANADO :) !!!");
            }
            game.print();
            System.out.println("Partida finalizada!\n");
        }

    }

    private Pos getCpuLaunchPos(Game game) {
        Double x = Math.random() * game.getN();
        Double y = Math.random() * game.getN();
        return new Pos(x.intValue(), y.intValue());
    }

    private Pos getUserLaunchPos(List<Object> arguments) {
        Pos pos = null;
        try {
            Double x = (Double) arguments.get(0);
            Double y = (Double) arguments.get(1);
            pos = new Pos(x.intValue(), y.intValue());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return pos;
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
