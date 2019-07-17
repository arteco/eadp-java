package com.arteco.eadp.java.java_04;

public class Persona {
    private String nombre;
    private String apellido;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString(){
        return 
         "Nombre :" + nombre + "\n" +
         "Apellido :" + apellido;
    }
}