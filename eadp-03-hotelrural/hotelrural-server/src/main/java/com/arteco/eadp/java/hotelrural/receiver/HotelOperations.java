package com.arteco.eadp.java.hotelrural.receiver;

import com.arteco.eadp.java.hotelrural.common.dto.*;

/**
 * Created by rarnau on 24/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public enum HotelOperations {
    availability(HotelAvailRequest.class),
    valuate(PreBookingRequest.class),
    confirmation(BookingRequest.class),
    cancellation(CancellationRequest.class),
    bookings(BookingsRequest.class),
    exit(ExitRq.class);

    private final Class<?> inputDtoClass;

    HotelOperations(Class<?> inputDtoClass) {
        this.inputDtoClass = inputDtoClass;
    }

    public static HotelOperations locateOf(Object dto) {
        if (dto != null) {
            Class<? extends Object> clazz = dto.getClass();
            for (HotelOperations op : values()) {
                if (clazz.isAssignableFrom(op.inputDtoClass)) {
                    return op;
                }
            }
        }
        return null;
    }


}
