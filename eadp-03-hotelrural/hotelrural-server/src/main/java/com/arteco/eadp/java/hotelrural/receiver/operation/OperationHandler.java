package com.arteco.eadp.java.hotelrural.receiver.operation;

import com.arteco.eadp.java.hotelrural.receiver.HotelServerSocket;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public interface OperationHandler<DTO, RES> {
    RES run(HotelServerSocket hotelServer, DTO dto);

    Class<DTO> getInputType();
}
