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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author rarnau
 */
public class InterpreteMain {

    public static void main(String[] args) throws Exception {
        Interprete interprete = new Interprete();

        String linea = leerEntradaUsuario();
        while (!"exit".equals(linea)) {
            Comando comando = interprete.procesaLinea(linea);

            if (comando != null) {
                String output = comando.ejecutar();
                System.out.println(output);
            } else {
                System.out.println("Comando no reconocido.");
                System.out.println("Los comandos disponibles son :" + List.of(Comandos.values()));
                System.out.println("Escriba [exit] para terminar");
                System.out.println("Por favor, inténtelo de nuevo.");
            }

            linea = leerEntradaUsuario();
        }
    }

    private static String leerEntradaUsuario() throws IOException {
        System.out.println("\n------------------");
        System.out.println("> Qué desea hacer?");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

}
