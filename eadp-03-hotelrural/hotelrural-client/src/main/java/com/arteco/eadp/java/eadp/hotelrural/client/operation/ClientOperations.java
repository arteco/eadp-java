package com.arteco.eadp.java.eadp.hotelrural.client.operation;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public enum ClientOperations {
    exit(new ClientExitOperation(), "finaliza la ejecución del cliente"),
    avail(new ClientAvailOperation(), "realiza una búsqueda de disponibilidad");

    private final ClientOperation handler;
    private final String description;

    ClientOperations(ClientOperation handler, String description) {
        this.handler = handler;
        this.description = description;
    }

    public ClientOperation getHandler() {
        return handler;
    }


    public String description() {
        return description;
    }
}
