package com.arteco.eadp.java.eadp.hotelrural.receiver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class HotelServer {
    private final ServerSocket serverSocket;
    private boolean end;

    public HotelServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        start();
    }

    public void start() throws IOException {
        while (!end) {
            Socket socket = this.serverSocket.accept();
            new HotelConnection(socket).start();
        }
    }

    public void terminate() {
        this.end = true;
    }
}
