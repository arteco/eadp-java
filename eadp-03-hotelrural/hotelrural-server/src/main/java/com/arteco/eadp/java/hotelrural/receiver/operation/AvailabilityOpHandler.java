package com.arteco.eadp.java.hotelrural.receiver.operation;


import com.arteco.eadp.java.hotelrural.common.dto.HotelAvailRequest;
import com.arteco.eadp.java.hotelrural.common.dto.HotelAvailResponse;
import com.arteco.eadp.java.hotelrural.receiver.HotelServerSocket;

import java.time.LocalDate;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class AvailabilityOpHandler implements OperationHandler<HotelAvailRequest, HotelAvailResponse> {
    @Override
    public HotelAvailResponse run(HotelServerSocket hotelServer, HotelAvailRequest o) {
        HotelAvailResponse rs = new HotelAvailResponse();
        rs.setFrom(o.getFrom());
        rs.setTo(LocalDate.now());
        return rs;
    }

    @Override
    public Class<HotelAvailRequest> getInputType() {
        return HotelAvailRequest.class;
    }
}
