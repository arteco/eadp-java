# Práctica 3 - Reserva hotelera

Este proyecto es la implementación de la práctica 3 del libro de Java: https://elartedeprogramar.net

## CONFIGURACION

Para poder iniciar el servidor debe indicar la Ip y puerto del servidor MysQL en
eadp-java\eadp-03-hotelrural\hotelrural-server\src\main\resources\META-INF\persistence.xml

## Requisitos de la práctica

El objetivo de esta práctica es crear un servicio accesible por red que permita reservar plazas hoteleras sobre un hotel
imaginario durante el més de agosto. Este servicio será consumido por una segunda aplicación Java que actuará de 
cliente y que interactivamente permitirá al usuario realizar operaciones sobre el sistema de reservas. 
 

Las reservas siempre serán sobre días completos desde las 12 am hasta las 12 am del día siguiente. Aunque una reserva
implique dos días, sólo se considerará que hay una reserva por día y es aquella que tiene la habitación reservada, por 
ejemplo a las 19h. Se entenderá que todas las habitaciones son dobles, con un único precio base por habitación. El hotel
tendrá dos tipos de habitaciones, normal o suite, con precios diferentes por tipo. También con dos tipos de régimen 
cada una de ellas: media pensión o completa. El régimen representará un % de incremento sobre el precio de la 
habitación. Una reserva será para sólo una habitación. Actualmente el hotel sólo dispone de 5 habitaciones regulares y 
2 suites.


El cliente y el servicio intercambiarán información mediante algún sistema de serialización de objetos, por ejemplo 
JSON. Tanto el servidor como el cliente usarán Sockets TCP/IP para establecer la conexión y poder intercambiar 
documentos. Los datos de las habitaciones disponibles, reservas y precios provendrán de una base de datos SQL usando 
JPA y QueryDSL. Para la gestión de disponibilidad se debe implementar un calendario usando cualquier estructura de 
información que permita saber qué habitaciones hay libres cada día de agosto. Se debe incorporar algún mecanismo que 
garantice que NO se hacen dos reservas sobre la misma habitación en los mismos días. 

El servidor debe exponer las siguientes operaciones:

1.  **availability**: Devuelve el listado de TIPOS de habitaciones disponibles entre dos fechas. Cada registro devuelto debe
    contener el TIPO de habitación al que hace referencia y un lista de los regímenes posibles para la habitación 
    indicando el precio estándar por día para ese régimen (y ese tipo de habitación). Si no hay ninguna habitación 
    disponible de un tipo, obviamente ese tipo se debe omitir en la respuesta. 
    
    1. _Entradas_: 
        1. from: LocalDate, 
        1. to: LocalDate
        1. roomType: Tipo de habitación. Opcional
            
    1. _Salidas_:
         1. Objeto de tipo HotelAvailResponse con la disponibilidad contenida dentro.
            
1.  **valuate**: Valorar una disponibilidad. Es decir, dadas dos fechas, el tipo de habitación y el régimen solicitado 
    obtiene el precio total de la posible reserva. Devuelve error si no está disponible.
    
    1. _Entradas_: Objeto validado del tipo PreBookingRequest con todos los campos obligatorios.
        1. from: LocalDate, 
        1. to: LocalDate
        1. roomType: Tipo de habitación
        1. mealPlan: Régimen
            
    1. _Salidas_:
        1.  Objeto de tipo PreBookingResponse conteniendo los parámetros de entrada, más un timestamp de cuándo se 
            realizó la operación y el precio de la posible reserva.
        
1.  **confirmation**: Realiza la reserva. Dadas dos fechas, el tipo de habitación, el régimen escogido y el código de 
    cliente (un String cualquiera). Ejecuta de nuevo una valoración y si tiene éxito bloquea dicha habitación durante 
    los días indicados. Devuelve información acerca de la reserva: el precio final, el identificador de la habitación y
    el identificador de la reserva. Esta operación debe modificar el calendario y la tabla de reservas.
    
    1. _Entradas_: Objeto validado de tipo BookingRequest. Todos los campos obligatorios.
        1. from: LocalDate, 
        1. to: LocalDate
        1. roomType: Tipo de habitación
        1. mealPlan: Régimen
        1. customer: String que identifica al cliente
        
    1. _Salidas_: Objeto de tipo BookingResponse que contiene toda la información de la reserva.
    
1.  **cancellation**: Dado un identificador de una reserva, libera la habitación para las fechas de la reserva dejando el
    calendario y la base de datos actualizada.
 
    1.  _Entradas_:
        1. bookingId: Identificador de la reserva.
        
    1.  _Salidas_: Confirmación de la operación realizada correctamente o error si no se encuentra la reserva en el 
        sistema.
    
1.  **bookings**: Listado de reservas, con posibilidad de indicar un rango de fechas opcional como filtro. Devuelve el 
    listado de reservas confirmadas no canceladas. Produce la misma información que la confirmación pero con aquellas 
    reservas que estén en el intervalo.

    1.  _Entradas_: 
        1. from: LocalDate
        1. to: LocalDate
        
    1.  _Salidas_: Listado de reservas en forma de Collection<BookingResponse>.
    
