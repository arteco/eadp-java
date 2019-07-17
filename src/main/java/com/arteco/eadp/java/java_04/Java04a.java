package com.arteco.eadp.java.java_04;

public class Java04a {
    public static void main(String[] args) {
        Persona p = new Persona();
        p.setNombre("José");
        p.setApellido("Jiménez");

        imprimirPersona(p);

        System.out.println(p);
    }

    public static void imprimirPersona(Persona p) {
        if (p != null) {
            System.out.println("Nombre :" + p.getNombre());
            System.out.println("Apellido :" + p.getApellido());
        }
    }
}