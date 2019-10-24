# Práctica 2 - Hundir la Float (con comandos)

Este proyecto es la implementación de la práctica 2 del libro de Java: https://elartedeprogramar.net

## Requisitos de la práctica

El objetivo es crear un sistema que permita jugar a hundir la flota contra la CPU. El juego debe consistir en la 
elección de casillas del tablero del oponente mediante un sistema de turnos y organizado en dos fases. La primera fase 
es la de colocación de los barcos en una matriz y la segunda fase comienza el sistema de turnos para lanzar misiles 
contra el oponente. Los requisitos del sistema son:

1. La interacción del usuario con el sistema de juego se hará mediante comandos por consola. Así que se debe crear un
     intérprete de comandos que lea la entrada del usuario. Posibilidad de usar librerías como commons-cli para tal fin.
     
1. Las operaciones que debe ofrecer ese intérprete como mínimo son: comenzar partida, lanzar misil, imprimir partida 
    (tablero e inventario) y salir.
    
1. La colocación de los barcos en el tablero, tanto para el jugador como para la CPU será aleatoriamente mediante tres 
    variables aleatorias a través de Math.random(). Las variables indicarán posición X e Y y si es horizontal o vertical. 
    Se permite que los barcos se toquen, dejando opcional la inclusión de algún mecanismo para evitarlo.
    
1. Se debe crear un log en un fichero de texto conteniendo las acciones que se realizan en todas las partidas. Tanto 
    las acciones del jugador como las de la CPU, marcando claramente el momento de inicio y fin de las partidas y su 
    resultado.
    
1. Tanto el tamaño de la matriz como el inventario de barcos debe ser configurable. Se supondrá una matriz cuadrada
    N x N. La variable global N determina el tamaño. El inventario vendrá dado por una instancia de List<Flota>. Siendo 
    la clase Flota = {nombre, numero, tamanyo}. Ambas determinará cuantos barcos se deberán colocar en cada partida. Se 
    deja opcional que el usuario pueda cambiar la configuración por comandos. Una configuración posible: 
    1. Tamaño de matriz por defecto: N = 10
    1. 1 portaaviones que ocupa 5 casillas.  2 cruceros, que ocupa 4 casillas.  3 destructores, que ocupen 3 casillas. 
        2 fragatas, de 2 casillas.  4 submarinos, de 1 casilla.
        
1. Si pasado un tiempo el usuario no indica su lanzamiento en medio de una partida en marcha, se le devuelve el turno 
    automáticamente a la CPU para que ejecute su turno.

El proyecto también deberá ser generado con Maven y con todos los casos de test unitarios que permitan validar el 90%
 del código fuente escrito.


## Notas de implementación

Cada una de las acciones posibles implementan la interfaz Action que describe los métodos necesario para poder ejecutar,
documentar y registrar las posibles acciones contempladas en la partida Game.

Para poder implementar un timeout sobre la entrada del usuario y puesto que la lectura de System.in es una operación
bloqueante para el thread que la lanza, no ha queda más remedio que contemplar la lectura del usuario desde un thread
secundario que pueda ser interrumpido. Este thread alojado en Parser.start() va leyendo la lectura del usuario y la 
transmite a una cola con todas las intrucciones del usuario. El thread principal del main va extrayendo las órdenes
registradas ejecutándolas sobre la partida en curso.
