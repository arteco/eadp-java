package com.arteco.eadp.java.hotelrural.receiver.operation;

import com.arteco.eadp.java.hotelrural.common.dto.ExitRq;
import com.arteco.eadp.java.hotelrural.receiver.HotelServerSocket;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class ExitOpHandler implements OperationHandler<ExitRq, Void> {
    @Override
    public Void run(HotelServerSocket hotelServer, ExitRq exitRq) {
        hotelServer.terminate();
        return null;
    }

    @Override
    public Class<ExitRq> getInputType() {
        return ExitRq.class;
    }
}
