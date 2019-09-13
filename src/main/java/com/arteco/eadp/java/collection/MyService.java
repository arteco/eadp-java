package com.arteco.eadp.java.collection;

import java.io.IOException;

/**
 * Created by rarnau on 13/09/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class MyService {

    /**
     * M&eacute;todo que devuelve el saludo
     * @see Persona
     * @since 1.3

     * @param name nombre de la persona a saludar
     * @return devuelve el mensaje compuesto del saludo con el nombre
     * @throws IOException si el nombre indicado es null
     *
     */
    public String sayHello(String name) throws IOException {
        if (name == null) {
            throw new IOException("Name can not be null");
        }
        return "hello " + name;
    }

}
