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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author rarnau
 */
public abstract class Comando {

    protected final List<String> args;
    protected final Interprete interprete;

    public Comando(Interprete interprete, List<String> args) {
        this.args = args;
        this.interprete = interprete;
    }


    public abstract String ejecutar() throws Exception;

    File localizaFichero(File directory, String destino) {
        File result = null;
        if (directory != null) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.getName().equals(destino)) {
                        result = f;
                        break;
                    }
                }
            }
        }
        return result;
    }

    File localizaOcreaFichero(File directory, String destino) throws IOException {
        File fileDest = localizaFichero(directory, destino);
        if (fileDest == null) {
            fileDest = new File(directory + File.separator + destino);
            fileDest.createNewFile();
        }
        return fileDest;
    }

    void escribirFichero(File fileDest, String data) throws IOException {
        if (fileDest != null && data != null) {
            FileOutputStream fis = new FileOutputStream(fileDest, true);
            fis.write(data.getBytes(StandardCharsets.UTF_8));
            fis.close();
        }
    }

    String leerFichero(File fileDest) throws IOException {
        String result;FileInputStream fis = new FileInputStream(fileDest);
        byte[] data = new byte[(int) fileDest.length()];
        fis.read(data);
        fis.close();
        result = new String(data);
        return result;
    }
}
