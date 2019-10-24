package com.arteco.eadp.java.eadp.hundirlaflota.game;

import com.arteco.eadp.java.eadp.hundirlaflota.action.Action;

import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rarnau on 23/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class Parser {

    private Game game;

    public Parser(Game game) {
        this.game = game;
    }

    /**
     * Procesa la entrada del usuario ejecutando las acciones que pueda identificar
     *
     * @param arguments argumentos de entrada del usuario [comando, arg1, arg2, ...]
     * @return devuelve la lista de acciones ejecutadas
     */
    public List<Action> processLine(List<Object> arguments) {
        List<Action> executed = new ArrayList<>();
        if (arguments.size() > 0) {
            game.getActions().stream()
                    .filter(a -> a.getName().equals(arguments.get(0)))
                    .forEach(a -> {
                        a.run(game, arguments.subList(1, arguments.size()));
                        executed.add(a);
                    });
        }
        return executed;
    }

    /**
     * Procesa la entrada plana de texto procedente del usuario para partirlo en trozos más pequeños, siendo el primero
     * el nombre de la acción a realizar, seguido de los argumentos que se encuentren
     *
     * @param line entrada del usuario
     * @return lista de argumentos
     */
    public List<Object> parseLine(String line) {
        List<Object> arguments = new ArrayList<>();
        try {
            StreamTokenizer streamTokenizer = new StreamTokenizer(new StringReader(line));
            while (streamTokenizer.nextToken() != StreamTokenizer.TT_EOF) {
                if (streamTokenizer.ttype == StreamTokenizer.TT_WORD) {
                    // leido una cadena de texto (String)
                    arguments.add(streamTokenizer.sval);
                } else if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
                    // leido un número (Double)
                    arguments.add(streamTokenizer.nval);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            arguments.clear();
        }
        return arguments;
    }

    /**
     * Devuelve la ayuda listando los posibles comandos que puede ejecutar el usuario
     *
     * @return un String formateado con la ayuda lista para ser impresa
     */
    public String printHelp() {
        StringBuilder sb = new StringBuilder();
        sb.append("Las acciones disponibles son:").append("\n");
        for (Action action : game.getActions()) {
            sb.append("\t").append(action.getName()).append(" -> ").append(action.getDescription()).append("\n");
        }
        return sb.toString();
    }
}
