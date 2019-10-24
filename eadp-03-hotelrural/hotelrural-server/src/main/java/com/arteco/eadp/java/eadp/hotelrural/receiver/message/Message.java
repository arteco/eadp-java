package com.arteco.eadp.java.eadp.hotelrural.receiver.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class Message {
    public static final String SEPARATOR = "----";


    private static final ObjectMapper objectMapper = new ObjectMapper();


    private final String content;
    private final String type;

    private Message(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public static Message of(InputStream inputStream) throws IOException {
        BufferedInputStream is = new BufferedInputStream(inputStream);
        String content = new String(is.readAllBytes());
        is.close();
        String[] parts = content.split(SEPARATOR);
        return new Message(parts[0], parts[1]);

    }

    public static Message of(Object res) throws JsonProcessingException {
        String content = res != null ? objectMapper.writeValueAsString(res) : "";
        return new Message(res != null ? res.getClass().getName() : "", content);
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    public Object toDto() throws ClassNotFoundException, IOException {
        Class<?> dtoClass = Class.forName(type);
        return objectMapper.readValue(content, dtoClass);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write((type + SEPARATOR + content).getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }
}
