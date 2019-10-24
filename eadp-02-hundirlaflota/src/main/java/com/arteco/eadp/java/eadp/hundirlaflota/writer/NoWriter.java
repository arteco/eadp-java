package com.arteco.eadp.java.eadp.hundirlaflota.writer;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class NoWriter implements Writer {
    @Override
    public Writer append(String s) {
        //do nothing
        return this;
    }
}
