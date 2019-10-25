package com.arteco.eadp.java.hotelrural.receiver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class HotelServerSocket {
    private final ServerSocket serverSocket;
    private boolean end;

    public HotelServerSocket(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("Server quedando a la escucha de nuevas peticiones en " + serverSocket.getLocalPort());
        while (!end) {
            Socket socket = this.serverSocket.accept();
            new HotelServer(this, socket).start();
        }
    }

    public void terminate() {
        this.end = true;
    }
}
