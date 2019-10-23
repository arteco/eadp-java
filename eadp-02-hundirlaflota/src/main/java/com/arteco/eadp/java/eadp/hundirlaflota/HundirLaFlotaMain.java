package com.arteco.eadp.java.eadp.hundirlaflota;

import com.arteco.eadp.java.eadp.hundirlaflota.accion.Action;
import com.arteco.eadp.java.eadp.hundirlaflota.juego.Game;
import com.arteco.eadp.java.eadp.hundirlaflota.juego.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Created by rarnau on 23/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class HundirLaFlotaMain {

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        Parser parser = new Parser(game);
        System.out.println("======================================");
        System.out.println("Bienvenido al juego de hundir la flota");
        System.out.println("======================================");
        String line = readLine();
        while (!"exit".equals(line)) {
            try {
                List<Action> actions = parser.processLine(line);
                if (actions == null || actions.size() == 0) {
                    String help = parser.printHelp();
                    System.out.println(help);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                line = readLine();
            }
        }
    }

    private static String readLine() throws IOException {
        Reader reader = new InputStreamReader(System.in);
        BufferedReader buf = new BufferedReader(reader);
        System.out.println("Indique su acción :");
        return buf.readLine();
    }

}
