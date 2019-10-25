package com.arteco.eadp.java.eadp.hotelrural.receiver.operation;

import com.arteco.eadp.java.eadp.hotelrural.common.dto.CancellationRequest;
import com.arteco.eadp.java.eadp.hotelrural.common.dto.CancellationResponse;
import com.arteco.eadp.java.eadp.hotelrural.receiver.HotelServerSocket;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class CancellationOpHandler implements OperationHandler<CancellationRequest, CancellationResponse> {
    @Override
    public CancellationResponse run(HotelServerSocket hotelServer, CancellationRequest o) {
        return null;
    }

    @Override
    public Class<CancellationRequest> getInputType() {
        return CancellationRequest.class;
    }
}
