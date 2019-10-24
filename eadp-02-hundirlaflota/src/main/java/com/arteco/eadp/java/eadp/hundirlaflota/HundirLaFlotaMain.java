package com.arteco.eadp.java.eadp.hundirlaflota;

import com.arteco.eadp.java.eadp.hundirlaflota.action.Action;
import com.arteco.eadp.java.eadp.hundirlaflota.game.Game;
import com.arteco.eadp.java.eadp.hundirlaflota.game.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by rarnau on 23/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class HundirLaFlotaMain {
    private static BlockingQueue<List<Object>> inputs = new LinkedBlockingDeque<>();

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        Parser parser = new Parser(game);
        System.out.println("======================================");
        System.out.println("Bienvenido al juego de hundir la flota");
        System.out.println("======================================");

        // inicializamos el juego automáticamente
        parser.executeActions(Collections.singletonList("start"));
        parser.start(inputs);

        while (!game.isEnd()) {
            System.out.println("Indique su acción :");

            // obtenemos la entrada de usuario con timeout, si no provoca un lanzamiento fuera del tablero
            List<Object> arguments = getArgumentsWithTimeout(10, "launch", -1., -1.);

            // Se ejecutan las acciones registradas
            List<Action> actions = parser.executeActions(arguments);

            // si el usuario ha escrito una línea en blanco se imprime la ayuda
            if (actions == null || actions.size() == 0) {
                String help = parser.printHelp();
                System.out.println(help);
            }

        }
    }

    private static List<Object> getArgumentsWithTimeout(int timeoutSeconds,
                                                        Object... defaultArguments) throws InterruptedException {

        List<Object> result = inputs.poll(timeoutSeconds, TimeUnit.SECONDS);
        if (result == null) {
            result = new ArrayList<>();
            if (defaultArguments != null) {
                result.addAll(Arrays.asList(defaultArguments));
            }
        }
        return result;
    }



}
