package com.arteco.eadp.java.eadp.hotelrural.receiver;

import com.arteco.eadp.java.eadp.hotelrural.receiver.operation.*;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public enum HotelOperations {
    availability(new AvailabilityOpHandler()),
    valuate(new ValuateOpHandler()),
    confirmation(new ConfirmationOpHandler()),
    cancellation(new CancellationOpHandler()),
    bookings(new ListOpHandler()),
    exit(new ExitOpHandler());

    private final OperationHandler handler;

    HotelOperations(OperationHandler handler) {
        this.handler = handler;
    }

    public static OperationHandler locateOf(Object dto) {
        if (dto != null) {
            Class<? extends Object> clazz = dto.getClass();
            for (HotelOperations op : values()) {
                if (clazz.isAssignableFrom(op.handler.getInputType())) {
                    return op.handler;
                }
            }
        }
        return null;
    }


}
