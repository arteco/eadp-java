package com.arteco.eadp.java.hotelrural.client.operation;

import com.arteco.eadp.java.hotelrural.client.cli.CliParser;
import com.arteco.eadp.java.hotelrural.client.cli.HotelClient;
import com.arteco.eadp.java.hotelrural.common.dto.ExitRq;

import java.io.IOException;

public class ClientTerminateOperation implements ClientOperation {
    @Override
    public Object run(CliParser parser, Object lastRequest) throws IOException {
        HotelClient c = parser.newHotelClient();
        c.send(new ExitRq());
        return null;
    }
}
