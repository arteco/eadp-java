package com.arteco.eadp.java.eadp.hundirlaflota.game;

import com.arteco.eadp.java.eadp.hundirlaflota.action.Action;
import com.arteco.eadp.java.eadp.hundirlaflota.action.ActionResult;
import com.arteco.eadp.java.eadp.hundirlaflota.action.ExitAction;
import com.arteco.eadp.java.eadp.hundirlaflota.writer.Writer;

import java.io.StreamTokenizer;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
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
    public List<ActionResult> executeActions(Writer writer, Object... arguments) {
        return executeActions(writer, Arrays.asList(arguments));
    }

    public List<ActionResult> executeActions(Writer writer, List<Object> arguments) {
        List<ActionResult> executed = new ArrayList<>();
        if (arguments.size() > 0) {
            game.getActions().stream()
                    .filter(a -> a.getName().equals(arguments.get(0)))
                    .forEach(a -> {
                        writer.append("Input = ").append(arguments.toString()).append(EOL);
                        String output = a.run(game, arguments.subList(1, arguments.size()));
                        writer.append(output);
                        executed.add(new ActionResult(output));
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

    public void start(BlockingQueue<List<Object>> inputs) {
        ByteBuffer buff = ByteBuffer.allocate(255);
        ReadableByteChannel channel = Channels.newChannel(System.in);
        this.reader = new Thread(() -> {
            boolean exitDetected = false;
            while (!game.isEnding() && !exitDetected) {
                try {
                    int res = channel.read(buff);
                    if (res > 0) {
                        String line = new String(buff.array(), 0, res);
                        buff.clear();
                        List<Object> args = parseLine(line);
                        inputs.add(args);
                        if (ExitAction.EXIT.equals(args.get(0))) {
                            exitDetected = true;
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.reader.setDaemon(true);
        this.reader.setName("User Input Thread");
        this.reader.start();
    }

}
