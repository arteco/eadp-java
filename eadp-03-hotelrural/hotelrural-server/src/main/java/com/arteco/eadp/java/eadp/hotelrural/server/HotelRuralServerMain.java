package com.arteco.eadp.java.eadp.hotelrural.server;

import com.arteco.eadp.java.eadp.hotelrural.receiver.HotelServerSocket;

/**
 * Created by rarnau on 23/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class HotelRuralServerMain {

    public static void main(String[] args) throws Exception {
        System.out.println("=====================================");
        System.out.println("Bienvenido al servidor del hotel rural:");
        System.out.println("=====================================");
        HotelServerSocket s = new HotelServerSocket(8080);
        s.start();
    }


}
