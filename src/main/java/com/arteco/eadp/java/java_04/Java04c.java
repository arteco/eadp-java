package com.arteco.eadp.java.java_04;

import com.arteco.eadp.java.java_04.java_4c.EstadoFactura;
import com.arteco.eadp.java.java_04.java_4c.Factura;

public class Java04c {

    public static void main(String[] args) {
        Factura f1 = new Factura("Acme SL", 100f, EstadoFactura.PAGADO);
        Factura f2 = new Factura("SociedadLimitada", 85f, EstadoFactura.PENDIENTE);
        System.out.println(f1);
        System.out.println(f2);
    }

   
  
}