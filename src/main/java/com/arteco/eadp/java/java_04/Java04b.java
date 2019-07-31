package com.arteco.eadp.java.java_04;

import com.arteco.eadp.java.java_04.java_04b.PersonaFisica;
import com.arteco.eadp.java.java_04.java_04b.PersonaJuridica;

public class Java04b {

    public static void main(String[] args) {
        PersonaFisica pf = new PersonaFisica("J. Jiménez", "12333444B");
        PersonaJuridica pj = new PersonaJuridica("Acme S.A.", "Z55333433");

        pf.imprimir();
        pj.imprimir();
    }

   
  
}