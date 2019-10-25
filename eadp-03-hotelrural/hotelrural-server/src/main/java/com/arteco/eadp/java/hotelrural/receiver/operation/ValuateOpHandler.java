package com.arteco.eadp.java.hotelrural.receiver.operation;

import com.arteco.eadp.java.hotelrural.common.dto.PreBookingRequest;
import com.arteco.eadp.java.hotelrural.common.dto.PreBookingResponse;
import com.arteco.eadp.java.hotelrural.receiver.HotelServerSocket;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class ValuateOpHandler implements OperationHandler<PreBookingRequest, PreBookingResponse> {

    @Override
    public PreBookingResponse run(HotelServerSocket hotelServer, PreBookingRequest valuateRq) {
        return null;
    }

    @Override
    public Class<PreBookingRequest> getInputType() {
        return PreBookingRequest.class;
    }
}
