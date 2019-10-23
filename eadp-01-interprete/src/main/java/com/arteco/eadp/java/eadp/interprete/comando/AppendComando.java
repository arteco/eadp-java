/*
 * Copyright 2019 rarnau.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.arteco.eadp.java.eadp.interprete.comando;

import com.arteco.eadp.java.eadp.interprete.Interprete;

import java.io.File;
import java.util.List;

/**
 * @author rarnau
 */
public class AppendComando extends Comando {

    public AppendComando(Interprete interprete, List<String> args) {
        super(interprete, args);
    }

    @Override
    public String ejecutar() throws Exception {
        String result = "Operación no realizada";
        if (args.size() > 0) {
            String destino = args.get(1);

            File fileDest = localizaOcreaFichero(interprete.getDirectory(), destino);

            String data = obtenerContenido();

            escribirFichero(fileDest, data);

            result = "Fichero modificado";

        }
        return result;
    }


    /**
     * Obtiene el resto de argumentos del usuario que irán como contenido a concatenar en el fichero
     *
     * @return devuelve el concatenado de todos con espacios
     */
    private String obtenerContenido() {
        List<String> contenido = args.subList(2, args.size());
        String data = "";
        for (String palabra : contenido) {
            data += palabra + " ";
        }
        data = data.trim() + "\n";
        return data;
    }


}
