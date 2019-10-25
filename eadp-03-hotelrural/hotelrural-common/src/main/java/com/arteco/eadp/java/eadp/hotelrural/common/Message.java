package com.arteco.eadp.java.eadp.hotelrural.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class Message {
    private static final String SEPARATOR = "----";


    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JavaTimeModule());
    }


    private final String content;
    private final String type;

    private Message(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public static Message of(Socket socket) throws IOException {
        Message message = of(socket.getInputStream());
        socket.shutdownInput();
        return message;
    }

    public static Message of(InputStream inputStream) throws IOException {
        byte[] bytes = inputStream.readAllBytes();
        String content = new String(bytes, StandardCharsets.UTF_8);
        String[] parts = content.split(SEPARATOR);
        return new Message(parts[0], parts[1]);

    }

    public static Message of(Object res) throws JsonProcessingException {
        String content = res != null ? objectMapper.writeValueAsString(res) : "";
        return new Message(res != null ? res.getClass().getName() : "", content);
    }

    public Object toDto() throws ClassNotFoundException, IOException {
        Class<?> dtoClass = Class.forName(type);
        return objectMapper.readValue(content, dtoClass);
    }

    public void writeTo(Socket socket) throws IOException {
        writeTo(socket.getOutputStream());
        socket.shutdownOutput();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write((type + SEPARATOR + content).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }

    public String getContent() {
        return content;
    }
}
