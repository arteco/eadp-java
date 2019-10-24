package com.arteco.eadp.java.eadp.hundirlaflota.game;

import com.arteco.eadp.java.eadp.hundirlaflota.action.Action;
import com.arteco.eadp.java.eadp.hundirlaflota.action.ExitAction;

import java.io.FileOutputStream;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import static com.arteco.eadp.java.eadp.hundirlaflota.action.Action.EOL;

/**
 * Created by rarnau on 23/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class Parser {

    private Game game;
    private Thread reader;

    public Parser(Game game) {
        this.game = game;
    }

    /**
     * Procesa la entrada del usuario ejecutando las acciones que pueda identificar
     *
     * @param arguments argumentos de entrada del usuario [comando, arg1, arg2, ...]
     * @return devuelve la lista de acciones ejecutadas
     */
    public List<Action> executeActions(List<Object> arguments) {
        List<Action> executed = new ArrayList<>();
        if (arguments.size() > 0) {
            game.getActions().stream()
                    .filter(a -> a.getName().equals(arguments.get(0)))
                    .forEach(a -> {
                        appendLog("Input = " + arguments.toString() + EOL);
                        String output = a.run(game, arguments.subList(1, arguments.size()));
                        appendLog(output);
                        executed.add(a);
                        if (a instanceof ExitAction) {

                            reader.i nterrupt();
                        }
                    });
        }
        return executed;
    }

    private void appendLog(String output) {
        System.out.println(output);
        try {
            FileOutputStream fos = new FileOutputStream("hundirlaflota.log", true);
            fos.write(output.getBytes(StandardCharsets.UTF_8));
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void start(BlockingQueue<List<Object>> inputs) {
        this.reader = new Thread(() -> {
            while (!game.isEnd()) {
                try {
                    byte[] bytes = new byte[255];
                    int n = System.in.read(bytes);
                    String line = new String(bytes, 0, n);
                    List<Object> args = parseLine(line);
                    inputs.add(args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.reader.start();
    }

}
