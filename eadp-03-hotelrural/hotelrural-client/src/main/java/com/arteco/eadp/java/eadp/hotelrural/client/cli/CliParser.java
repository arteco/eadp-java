package com.arteco.eadp.java.eadp.hotelrural.client.cli;

import com.arteco.eadp.java.eadp.hotelrural.client.operation.ClientOperations;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class CliParser {

    private final BufferedReader reader;
    private final PrintStream printer;
    private final String serverHost;
    private final int serverPort;
    private String[] parts;
    private String datePattern = "dd/MM/yyyy";

    public CliParser(String serverHost, int serverPort, InputStream in, PrintStream printStream) {
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.printer = printStream;
        printer.println("=====================================");
        printer.println("Bienvenido al cliente del hotel rural:");
        printer.println("=====================================");
        printer.println("Servidor utilizado : " + serverHost + ":" + serverPort);
        printer.println("=====================================");
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public boolean hasNext() {
        printer.println("Escriba el siguiente comando:");
        try {
            String line = reader.readLine();
            parts = line.split(" ");
            if (parts.length > 0 && "exit".equals(parts[0])) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String[] next() {
        return parts;
    }

    public void printHelp() {
        printer.println("==============================");
        printer.println("Los comandos disponibles son: ");
        for (ClientOperations op : ClientOperations.values()) {
            printer.println("\t" + op.name() + " -> " + op.description());
        }
        printer.println("==============================");
    }

    public void printError(Exception e) {
        e.printStackTrace(printer);
    }

    public String getServerHost() {
        return serverHost;
    }

    public int getServerPort() {
        return serverPort;
    }

    public HotelClient newHotelClient() {
        return new HotelClient(getServerHost(), getServerPort());
    }

    public LocalDate getUserInputDate(String nombre) {
        System.out.println("Indique [" + nombre + "] con " + datePattern + ":");
        try {
            String raw = reader.readLine();
            if (raw.length() > 0) {
                return LocalDate.parse(raw, DateTimeFormatter.ofPattern(datePattern));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public <ENUM extends Enum<ENUM>> ENUM getUserInputEnum(Class<ENUM> enumClass, String nombre) {
        System.out.println("Indique [" + nombre + "], opciones " + EnumSet.allOf(enumClass) + ":");
        try {
            String raw = reader.readLine();
            if (raw.length() > 0) {
                return Enum.valueOf(enumClass, raw);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
