package com.arteco.eadp.java.hotelrural.client.operation;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public enum ClientOperations {
    EXIT(new ClientNoOperation(), "finaliza la ejecución del cliente"),
    REPEAT(new ClientNoOperation(), "reproduce la última operación ejecutada"),
    TERMINATE(new ClientTerminateOperation(), "finaliza el servidor"),
    AVAIL(new ClientAvailOperation(), "realiza una búsqueda de disponibilidad"),
    VALUATE(new ClientPreBookOperation(), "realiza una simulación de una reserva"),
    CONFIRM(new ClientBookOperation(), "confirma una reserva para unas fechas indicadas"),
    CANCEL(new ClientCancelOperation(), "cancela una reserva confirmada"),
    LIST(new ClientListOperation(), "lista las reservas almacenadas");

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
