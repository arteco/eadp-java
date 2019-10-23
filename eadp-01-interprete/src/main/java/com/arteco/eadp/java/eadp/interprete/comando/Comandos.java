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

/**
 * @author rarnau
 */
public enum Comandos {
    LS(LsComando.class),
    CD(CdComando.class),
    CAT(CatComando.class),
    RM(RmComando.class),
    MKDIR(MkDirComando.class),
    CP(CpComando.class),
    APPEND(AppendComando.class),
    REPLACE(ReplaceComando.class);;
    private Class<? extends Comando> clase;

    Comandos(Class<? extends Comando> clase) {
        this.clase = clase;
    }

    public Class<? extends Comando> getClase() {
        return clase;
    }

}
