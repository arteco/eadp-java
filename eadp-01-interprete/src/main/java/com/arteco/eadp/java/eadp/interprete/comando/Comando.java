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

    /**
     * Método abstracto de obligatoria implementación en cada uno de los comandos
     *
     * @return devuelve la salida generada por la ejecución del comando
     * @throws Exception permite lanzar excepciones hacia arriba
     */
    public abstract String ejecutar() throws Exception;

    /**
     * Localiza el fichero contenido dentro del directorio según el nombre indicado en el segundo argumento.
     * Si no lo encuentra devuelve null.
     *
     * @param directory directorio donde buscar
     * @param destino   nombre del fichero a localizar en el directorio
     * @return devuelve File si encuentra el fichero dentro del directorio
     */
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

    /**
     * Localiza el fichero contenido dentro del directorio según el nombre indicado en el segundo argumento.
     * Si no lo encuentra creará un fichero vacío.
     *
     * @param directory directorio donde buscar o crear si es necesario
     * @param destino   nombre del fichero a localizar o crear en el directorio
     * @return devuelve File del fichero encontraro o creado dentro del directorio
     */
    File localizaOcreaFichero(File directory, String destino) throws IOException {
        File fileDest = localizaFichero(directory, destino);
        if (fileDest == null) {
            fileDest = new File(directory + File.separator + destino);
            fileDest.createNewFile();
        }
        return fileDest;
    }

    /**
     * Escribe el contenido de data dentro del fichero indicado
     *
     * @param fileDest fichero de destino donde escribir
     * @param data     datos a volcar dentro del fichero
     * @throws IOException si no puede escribir en el fichero lanzará error
     */
    void escribirFichero(File fileDest, String data) throws IOException {
        if (fileDest != null && data != null) {
            FileOutputStream fis = new FileOutputStream(fileDest, true);
            fis.write(data.getBytes(StandardCharsets.UTF_8));
            fis.close();
        }
    }

    /**
     * Operación para leer el contenido de un fichero en un String.
     *
     * @param fileDest fichero a leer
     * @return contenido en String del fichero leído
     * @throws IOException error en caso de no poder leer el fichero
     */
    String leerFichero(File fileDest) throws IOException {
        String result;
        FileInputStream fis = new FileInputStream(fileDest);
        byte[] data = new byte[(int) fileDest.length()];
        fis.read(data);
        fis.close();
        result = new String(data);
        return result;
    }
}
