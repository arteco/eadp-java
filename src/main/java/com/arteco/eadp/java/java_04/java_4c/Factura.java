package com.arteco.eadp.java.java_04.java_4c;

public class Factura {
    private static int contador = 0;
    private EstadoFactura estado;
    private Float importe;
    private String cliente;
    private int id;

    public Factura(String cliente, Float importe, EstadoFactura estado) {
        this.cliente = cliente;
        this.importe = importe;
        this.estado = estado;
        this.id = ++Factura.contador;
    }

    public EstadoFactura getEstado() {
        return estado;
    }

    public Float getImporte() {
        return importe;
    }

    public String getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "Factura " + id + "/" + contador + " -> " + cliente + ", importe: " + importe + ", estado: " + estado;
    }
}