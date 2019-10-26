package com.arteco.eadp.java.hotelrural.client.operation;

import com.arteco.eadp.java.hotelrural.client.cli.CliParser;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class ClientNoOperation implements ClientOperation {
    @Override
    public Object run(CliParser parser, Object lastRequest) {
        // do nothing;
        return null;
    }
}
