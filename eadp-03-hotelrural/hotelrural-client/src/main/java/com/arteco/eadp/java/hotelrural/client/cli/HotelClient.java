package com.arteco.eadp.java.hotelrural.client.cli;

import com.arteco.eadp.java.hotelrural.common.Message;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class HotelClient {

    public static final String SERVER_NAME = "localhost";
    public static final int SERVER_PORT = 8080;

    private final String host;
    private final int port;

    public HotelClient() {
        this(SERVER_NAME, SERVER_PORT);
    }

    public HotelClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object send(Object rq) throws IOException {
        Object dto = null;
        Socket socket = null;
        try {
            Message requestMessage = Message.of(rq);
            socket = new Socket(host, port);
            socket.setSoTimeout(120 * 1000);

            requestMessage.writeTo(socket);

            Message responseMessage = Message.of(socket);

            System.out.println("Received from server " + responseMessage.getContent());

            dto = responseMessage.toDto();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
        return dto;
    }
}
