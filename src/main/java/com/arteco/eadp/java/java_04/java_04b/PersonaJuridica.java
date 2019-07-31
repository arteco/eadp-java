package com.arteco.eadp.java.java_04.java_04b;

public class PersonaJuridica extends Persona {
    private final String cif;

    public PersonaJuridica(String nombre, String cif) {
        super(nombre);
        this.cif = cif;
    }

    public void imprimir() {
        System.out.println(nombre + ", cif=" + cif);
    }
}