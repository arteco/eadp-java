package com.arteco.eadp.java.java_04.java_04b;

public abstract class Persona {
    protected final String nombre;

    protected Persona(String nombre) {
        this.nombre = nombre;
    }

    public abstract void imprimir();
}