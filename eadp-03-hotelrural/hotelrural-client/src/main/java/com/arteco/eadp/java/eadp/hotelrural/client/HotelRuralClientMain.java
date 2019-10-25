package com.arteco.eadp.java.eadp.hotelrural.client;

import com.arteco.eadp.java.eadp.hotelrural.client.cli.CliParser;
import com.arteco.eadp.java.eadp.hotelrural.client.cli.HotelClient;
import com.arteco.eadp.java.eadp.hotelrural.client.operation.ClientOperations;

/**
 * Created by rarnau on 23/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class HotelRuralClientMain {

    public static void main(String[] args) {
        String serverHost = HotelClient.SERVER_NAME;
        int serverPort = HotelClient.SERVER_PORT;
        if (args.length > 1) {
            serverHost = args[0];
            serverPort = Integer.parseInt(args[1]);
        }
        CliParser parser = new CliParser(serverHost, serverPort, System.in, System.out);
        while (parser.hasNext()) {
            String[] command = parser.next();
            try {
                ClientOperations operation = ClientOperations.valueOf(command[0]);
                operation.getHandler().run(parser);
            } catch (Exception e) {
                parser.printError(e);
                parser.printHelp();
            }
        }
    }


}
