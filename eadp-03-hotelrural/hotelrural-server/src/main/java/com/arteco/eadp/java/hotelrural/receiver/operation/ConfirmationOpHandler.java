package com.arteco.eadp.java.hotelrural.receiver.operation;

import com.arteco.eadp.java.hotelrural.common.dto.BookingRequest;
import com.arteco.eadp.java.hotelrural.common.dto.BookingResponse;
import com.arteco.eadp.java.hotelrural.receiver.HotelServerSocket;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class ConfirmationOpHandler implements OperationHandler<BookingRequest, BookingResponse> {
    @Override
    public BookingResponse run(HotelServerSocket hotelServer, BookingRequest o) {
        return null;
    }

    @Override
    public Class<BookingRequest> getInputType() {
        return BookingRequest.class;
    }


}
