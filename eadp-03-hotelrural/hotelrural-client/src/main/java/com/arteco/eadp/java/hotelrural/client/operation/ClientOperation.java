package com.arteco.eadp.java.hotelrural.client.operation;

import com.arteco.eadp.java.hotelrural.client.cli.CliParser;

import java.io.IOException;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public interface ClientOperation {
    Object run(CliParser parser, Object lastRequest) throws IOException;
}
