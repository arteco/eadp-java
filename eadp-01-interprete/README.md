# Práctica 2 - Intérprete de comnados (con comandos)

Este proyecto es la implementación de la práctica 1 del libro de Java: https://elartedeprogramar.net

## Requisitos de la práctica

El objetivo de esta práctica es la realización de un intérprete de comandos que permita al usuario introducir sentencias
 desde el teclado de forma interactiva. A través de esos comandos el usuario podrá ejecutar instrucciones que permitan 
 manipular el sistema de ficheros de forma básica. Las operaciones que debe poder ejecutar son: 
 
1. ls → listar los ficheros del directorio actual
1. cd <arg> → cambiar de directorio. 
1. cat <arg> → imprimir el contenido de un fichero
1. rm <arg> → eliminar el fichero o directorio indicado
1. mkdir <arg> → crea el directorio indicado en el nivel actual
1. cp <arg1> <arg2> → copiar el fichero de arg1 en la ubicación arg2.
1. append <arg1> <arg2> → añade el contenido de arg2 al final del archivo indicado en arg1. 
1. replace <arg1> <arg2> <arg3> → reemplaza el contenido del fichero indicado en arg1 buscando las referencias de arg2 
    para sustituirlas por arg3. 
1. exit → finaliza la ejecución del programa

Para poder llevar a cabo esta tarea, el programador primero deberá crear un proyecto Maven mono módulo usando las 
instrucciones indicadas en el capítulo 1. El asistente dejará a disposición de éste una clase conteniendo el método 
public static void main que se usará para crear el bucle principal. El programador deberá leer la entrada del usuario 
mediante System.in mientras que el usuario no escriba exit. 

Hasta entonces, se ejecutará cada uno de los comandos escritos por el usuario. El programa delegará la ejecución de la 
acción solicitada en una clase determinada. Deberá haber tantas clases específicas como comandos enumerados. Para 
validar el desarrollo debe haber tantos test unitarios como los necesarios para alcanzar una cobertura por encima del 
90% del código fuente.

