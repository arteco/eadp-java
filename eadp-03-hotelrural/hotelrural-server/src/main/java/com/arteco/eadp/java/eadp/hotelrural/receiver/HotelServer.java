package com.arteco.eadp.java.eadp.hotelrural.receiver;

import com.arteco.eadp.java.eadp.hotelrural.common.Message;
import com.arteco.eadp.java.eadp.hotelrural.receiver.operation.OperationHandler;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.net.Socket;
import java.util.Set;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class HotelServer extends Thread {


    private final static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final static Validator validator = factory.getValidator();
    private final HotelServerSocket hotelServer;
    private final Socket socket;

    public HotelServer(HotelServerSocket hotelServer, Socket socket) {
        this.hotelServer = hotelServer;
        this.socket = socket;
    }

    public void run() {
        try {

            Message response;

            Message request = Message.of(socket);

            Object dto = request.toDto();
            Set<ConstraintViolation<Object>> errors = validator.validate(dto);

            if (errors.isEmpty()) {

                OperationHandler handler = HotelOperations.locateOf(dto);
                System.out.println("Received from client " + request.getContent());
                Object res = handler.run(hotelServer, dto);

                response = Message.of(res);
            } else {
                response = Message.of(errors);
            }

            response.writeTo(socket);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
