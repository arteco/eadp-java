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
    public String run(Game game, List<Object> arguments) {
        StringBuilder stb = new StringBuilder();

        Impact impactUSR;
        Impact impactCPU;

        Pos pos = getUserLaunchPos(arguments);
        if (pos != null) {
            impactUSR = game.getBoardCPU().evaluateImpact(pos);
            stb.append("User -> ").append(impactUSR).append(EOL);
        }

        pos = getCpuLaunchPos(game);
        impactCPU = game.getBoardUSR().evaluateImpact(pos);
        stb.append("Cpu  -> ").append(impactCPU).append(EOL);


        boolean isUsrAlive = game.getBoardUSR().isAlive();
        boolean isCpuAlive = game.getBoardCPU().isAlive();

        if (!isUsrAlive || !isCpuAlive) {
            stb.append(EOL);
            if (isCpuAlive == isUsrAlive) {
                stb.append("¡¡¡EMPATE!!!");
            } else if (isCpuAlive) {
                stb.append("¡¡¡OPS, HA PERDIDO :( !!!");
            } else {
                stb.append("¡¡¡ENHORABUENA, HA GANADO :) !!!");
            }
            stb.append(EOL);
            stb.append(game.print());
            stb.append("Partida finalizada!").append(EOL);
            stb.append(EOL);
        }
        return stb.toString();
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
            // System.err.println(e.getMessage());
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
