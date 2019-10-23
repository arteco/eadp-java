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
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * @author rarnau
 */
public class CpComando extends Comando {

    public CpComando(Interprete interprete, List<String> args) {
        super(interprete, args);
    }

    @Override
    public String ejecutar() throws IOException {
        String result = "Operación no realizada";
        if (args.size() > 1) {
            String origen = args.get(1);
            String destino = args.get(2);
            File fileOrig = localizaFichero(interprete.getDirectory(), origen);
            if (fileOrig != null) {
                File fileDest = localizaOcreaFichero(interprete.getDirectory(), destino);
                Files.copy(fileOrig.toPath(), fileDest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                result = "Fichero copiado";
            }

        }
        return result;
    }

}
