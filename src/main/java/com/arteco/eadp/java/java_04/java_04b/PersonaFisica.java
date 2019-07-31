package com.arteco.eadp.java.java_04.java_04b;

public class PersonaFisica extends Persona {
    private final String dni;

    public PersonaFisica(String nombre, String dni) {
        super(nombre);
        this.dni = dni;
    }

    public void imprimir() {
        System.out.println(nombre + ", dni=" + dni);
    }
}
