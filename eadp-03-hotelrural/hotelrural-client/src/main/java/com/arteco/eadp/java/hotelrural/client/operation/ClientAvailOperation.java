package com.arteco.eadp.java.hotelrural.client.operation;

import com.arteco.eadp.java.hotelrural.client.cli.CliParser;
import com.arteco.eadp.java.hotelrural.client.cli.HotelClient;
import com.arteco.eadp.java.hotelrural.common.dto.HotelAvailRequest;
import com.arteco.eadp.java.hotelrural.common.dto.HotelAvailResponse;
import com.arteco.eadp.java.hotelrural.common.dto.inner.RoomType;

import java.io.IOException;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class ClientAvailOperation implements ClientOperation {
    @Override
    public void run(CliParser parser) throws IOException {
        HotelClient c = parser.newHotelClient();

        HotelAvailRequest rq = new HotelAvailRequest();
        rq.setFrom(parser.getUserInputDate("fecha desde"));
        rq.setTo(parser.getUserInputDate("fecha hasta"));
        rq.setRoomType(parser.getUserInputEnum(RoomType.class, "tipo de habitación"));

        HotelAvailResponse rs = (HotelAvailResponse) c.send(rq);

    }

}
