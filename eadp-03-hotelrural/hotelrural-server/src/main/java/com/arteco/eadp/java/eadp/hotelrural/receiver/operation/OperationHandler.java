package com.arteco.eadp.java.eadp.hotelrural.receiver.operation;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public interface OperationHandler<DTO, RES> {
    RES run(DTO dto);

    Class<DTO> getInputType();
}
