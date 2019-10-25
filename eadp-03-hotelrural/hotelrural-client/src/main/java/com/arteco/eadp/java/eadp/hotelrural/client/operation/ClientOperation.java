package com.arteco.eadp.java.eadp.hotelrural.client.operation;

import com.arteco.eadp.java.eadp.hotelrural.client.cli.CliParser;

import java.io.IOException;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public interface ClientOperation {
    void run(CliParser parser) throws IOException, ClassNotFoundException;
}
