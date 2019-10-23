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
import java.io.IOException;
import java.util.List;

/**
 * @author rarnau
 */
public class ReplaceComando extends Comando {

    public ReplaceComando(Interprete interprete, List<String> args) {
        super(interprete, args);
    }

    @Override
    public String ejecutar() throws IOException {
        String result = "Operación no realizada";
        if (args.size() > 2) {
            String destino = args.get(1);
            String buscado = args.get(2);
            String reemplazo = args.get(3);

            File fileDest = localizaFichero(interprete.getDirectory(), destino);
            if (fileDest != null) {

                String contenido = leerFichero(fileDest);

                contenido = contenido.replace(buscado, reemplazo);

                escribirFichero(fileDest, contenido);

                result = "Contenido reemplazado";
            }
        }
        return result;
    }

}
