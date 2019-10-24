package com.arteco.eadp.java.eadp.hotelrural.server;

import com.arteco.eadp.java.eadp.hotelrural.receiver.HotelServer;

/**
 * Created by rarnau on 23/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class HotelRuralServerMain {

    public static void main(String[] args) throws Exception {
        HotelServer s = new HotelServer(8080);
        s.start();
    }


}
