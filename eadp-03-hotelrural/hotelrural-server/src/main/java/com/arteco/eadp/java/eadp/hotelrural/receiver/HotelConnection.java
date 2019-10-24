package com.arteco.eadp.java.eadp.hotelrural.receiver;

import com.arteco.eadp.java.eadp.hotelrural.receiver.message.Message;
import com.arteco.eadp.java.eadp.hotelrural.receiver.operation.OperationHandler;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Set;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class HotelConnection extends Thread {


    private final static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final static Validator validator = factory.getValidator();
    private final OutputStream outputStram;
    private final InputStream inputStream;

    public HotelConnection(Socket socket) throws IOException {
        this(socket.getInputStream(), socket.getOutputStream());

    }

    public HotelConnection(InputStream is, OutputStream os) {
        this.inputStream = is;
        this.outputStram = os;
    }

    public void run() {
        try {
            Message response;
            Message request = Message.of(inputStream);

            Object dto = request.toDto();
            Set<ConstraintViolation<Object>> errors = validator.validate(dto);

            if (errors.isEmpty()) {
                OperationHandler handler = HotelOperations.locateOf(dto);
                Object res = handler.run(dto);
                response = Message.of(res);
            } else {
                response = Message.of(errors);
            }

            response.writeTo(outputStram);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
