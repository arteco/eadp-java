package com.arteco.eadp.java.eadp.hundirlaflota.action;

import com.arteco.eadp.java.eadp.hundirlaflota.game.Game;

import java.util.List;

public interface Action {
    String EOL = "\n";

    /**
     * Ejecuta la acción sobre el juego indicado por parámetros según los argumentos citados
     * Si la acción no requiere argumentos, éstos se podrán ignorar
     *
     * @param game      partida en curso sobre la que aplicar la acción
     * @param arguments argumentos introducidos por el usuario
     */
    String run(Game game, List<Object> arguments);

    /**
     * Devuelve la descripción de la acción para la impresión de la ayuda
     *
     * @return String con la descripción
     */
    String getDescription();

    /**
     * Permite registrar la acción con un nombre para ser invocada por el usuario
     *
     * @return nombre de la acción
     */
    String getName();
}
