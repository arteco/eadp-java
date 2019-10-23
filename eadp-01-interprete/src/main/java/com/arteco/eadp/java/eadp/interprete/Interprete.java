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
package com.arteco.eadp.java.eadp.interprete;

import com.arteco.eadp.java.eadp.interprete.comando.Comando;
import com.arteco.eadp.java.eadp.interprete.comando.Comandos;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

/**
 * @author rarnau
 */
public class Interprete {

    private File directory;

    public Interprete() {
        // usa el directorio actual
        this(".");
    }

    public Interprete(String path) {
        // usa el directorio actual
        this.directory = new File(path);
        System.out.println("\nCreado el intérprete en " + this.directory.getAbsolutePath());
    }

    Comando procesaLinea(String linea) {
        Comando result = null;
        try {
            List<String> parts = Arrays.asList(linea.split(" "));
            Comandos comando = Comandos.valueOf(parts.get(0).toUpperCase());
            Constructor<? extends Comando> constructor = comando.getClase().getConstructor(Interprete.class, List.class);
            result = constructor.newInstance(this, parts);
        } catch (Exception e) {
            System.err.println("Error :" + e.getMessage());
        }
        return result;
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }
}
