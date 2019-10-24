package com.arteco.eadp.java.eadp.hundirlaflota.writer;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class MixedWriter implements Writer {

    @Override
    public Writer append(String output) {
        System.out.println(output);
        try {
            FileOutputStream fos = new FileOutputStream("hundirlaflota.log", true);
            fos.write(output.getBytes(StandardCharsets.UTF_8));
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

}
