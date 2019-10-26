package com.arteco.eadp.java.hotelrural.client.operation;

import com.arteco.eadp.java.hotelrural.client.cli.CliParser;
import com.arteco.eadp.java.hotelrural.client.cli.HotelClient;
import com.arteco.eadp.java.hotelrural.common.dto.BookingListRequest;
import com.arteco.eadp.java.hotelrural.common.dto.BookingListResponse;

import java.io.IOException;

public class ClientListOperation implements ClientOperation {
    @Override
    public Object run(CliParser parser, Object lastRequest) throws IOException {
        HotelClient c = parser.newHotelClient();

        BookingListRequest rq;
        if(lastRequest instanceof BookingListRequest){
            rq = (BookingListRequest) lastRequest;
        }else {
            rq = new BookingListRequest();
            rq.setFrom(parser.getUserInputDate("fecha desde"));
            rq.setTo(parser.getUserInputDate("fecha hasta"));
        }

        BookingListResponse rs = (BookingListResponse) c.send(rq);
        parser.printMessage("Listado de reservas:");
        if (rs != null && rs.getBookings() != null) {
            rs.getBookings().forEach(bookData -> parser.printMessage("\t"+bookData.toString()));
        }
        return rq;
    }
}
