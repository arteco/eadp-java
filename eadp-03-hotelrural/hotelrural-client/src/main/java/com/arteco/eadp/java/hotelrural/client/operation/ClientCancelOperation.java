package com.arteco.eadp.java.hotelrural.client.operation;

import com.arteco.eadp.java.hotelrural.client.cli.CliParser;
import com.arteco.eadp.java.hotelrural.client.cli.HotelClient;
import com.arteco.eadp.java.hotelrural.common.dto.CancellationRequest;
import com.arteco.eadp.java.hotelrural.common.dto.CancellationResponse;

import java.io.IOException;

public class ClientCancelOperation implements ClientOperation {
    @Override
    public Object run(CliParser parser, Object lastRequest) throws IOException {
        HotelClient c = parser.newHotelClient();

        CancellationRequest rq;
        if (lastRequest instanceof CancellationRequest){
            rq = (CancellationRequest) lastRequest;
        }else {
            rq = new CancellationRequest();
            rq.setBookingId(parser.getUserInputLong("identificador de la reserva"));
        }

        CancellationResponse rs = (CancellationResponse) c.send(rq);
        if (rs != null) {
            parser.printMessage("Reserva cancelada");
        }
        return rq;
    }
}
