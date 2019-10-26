package com.arteco.eadp.java.hotelrural.client.operation;

import com.arteco.eadp.java.hotelrural.client.cli.CliParser;
import com.arteco.eadp.java.hotelrural.client.cli.HotelClient;
import com.arteco.eadp.java.hotelrural.common.dto.BookingRequest;
import com.arteco.eadp.java.hotelrural.common.dto.BookingResponse;
import com.arteco.eadp.java.hotelrural.common.dto.inner.MealPlan;
import com.arteco.eadp.java.hotelrural.common.dto.inner.RoomType;

import java.io.IOException;

public class ClientBookOperation implements ClientOperation {
    @Override
    public Object run(CliParser parser, Object lastRequest) throws IOException {
        HotelClient c = parser.newHotelClient();

        BookingRequest rq;
        if (lastRequest instanceof BookingRequest){
            rq = (BookingRequest) lastRequest;
        }else {
            rq = new BookingRequest();
            rq.setFrom(parser.getUserInputDate("fecha desde"));
            rq.setTo(parser.getUserInputDate("fecha hasta"));
            rq.setRoomType(parser.getUserInputEnum(RoomType.class, "tipo de habitación"));
            rq.setMealPlan(parser.getUserInputEnum(MealPlan.class, "régimen"));
            rq.setCustomerId(parser.getUserInputString("identificador del cliente"));
        }

        BookingResponse rs = (BookingResponse) c.send(rq);
        parser.printMessage("Valoración de la reserva:");
        if (rs != null) {
            parser.printMessage("\tbookingId : " + rs.getBookingId());
            parser.printMessage("\troomId : " + rs.getRoomId());
        }
        return rq;
    }
}
