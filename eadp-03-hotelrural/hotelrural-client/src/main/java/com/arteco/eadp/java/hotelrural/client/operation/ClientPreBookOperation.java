package com.arteco.eadp.java.hotelrural.client.operation;

import com.arteco.eadp.java.hotelrural.client.cli.CliParser;
import com.arteco.eadp.java.hotelrural.client.cli.HotelClient;
import com.arteco.eadp.java.hotelrural.common.dto.PreBookingRequest;
import com.arteco.eadp.java.hotelrural.common.dto.PreBookingResponse;
import com.arteco.eadp.java.hotelrural.common.dto.inner.MealPlan;
import com.arteco.eadp.java.hotelrural.common.dto.inner.RoomType;

import java.io.IOException;

public class ClientPreBookOperation implements ClientOperation {
    @Override
    public Object run(CliParser parser, Object lastRequest) throws IOException {
        HotelClient c = parser.newHotelClient();

        PreBookingRequest rq;
        if (lastRequest instanceof PreBookingRequest) {
            rq = (PreBookingRequest) lastRequest;
        } else {
            rq = new PreBookingRequest();
            rq.setFrom(parser.getUserInputDate("fecha desde"));
            rq.setTo(parser.getUserInputDate("fecha hasta"));
            rq.setRoomType(parser.getUserInputEnum(RoomType.class, "tipo de habitación"));
            rq.setMealPlan(parser.getUserInputEnum(MealPlan.class, "régimen"));
        }

        PreBookingResponse rs = (PreBookingResponse) c.send(rq);
        parser.printMessage("Valoración de la reserva:");
        if (rs != null) {
            parser.printMessage("\tPrecio : " + rs.getPrice());
        }
        return rq;
    }
}
