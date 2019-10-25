package com.arteco.eadp.java.eadp.hotelrural.receiver.operation;

import com.arteco.eadp.java.eadp.hotelrural.common.dto.BookingsRequest;
import com.arteco.eadp.java.eadp.hotelrural.common.dto.BookingsResponse;
import com.arteco.eadp.java.eadp.hotelrural.receiver.HotelServerSocket;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class ListOpHandler implements OperationHandler<BookingsRequest, BookingsResponse> {
    @Override
    public BookingsResponse run(HotelServerSocket hotelServer, BookingsRequest o) {
        return null;
    }

    @Override
    public Class<BookingsRequest> getInputType() {
        return BookingsRequest.class;
    }
}
