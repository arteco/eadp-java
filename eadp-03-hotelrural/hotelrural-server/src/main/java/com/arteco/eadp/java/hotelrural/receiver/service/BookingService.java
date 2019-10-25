package com.arteco.eadp.java.hotelrural.receiver.service;

import com.arteco.eadp.java.hotelrural.common.dto.*;
import com.arteco.eadp.java.hotelrural.common.dto.base.MealPlanData;
import com.arteco.eadp.java.hotelrural.common.dto.inner.MealPlan;
import com.arteco.eadp.java.hotelrural.common.dto.inner.RoomAvail;
import com.arteco.eadp.java.hotelrural.common.dto.inner.RoomPrice;
import com.arteco.eadp.java.hotelrural.common.dto.inner.RoomType;
import com.arteco.eadp.java.hotelrural.receiver.HotelOperations;
import com.arteco.eadp.java.hotelrural.receiver.HotelServerSocket;
import com.arteco.eadp.java.hotelrural.receiver.persistence.dao.BookingDao;
import com.arteco.eadp.java.hotelrural.receiver.persistence.model.Booking;
import com.arteco.eadp.java.hotelrural.receiver.persistence.model.Price;
import com.arteco.eadp.java.hotelrural.receiver.persistence.model.Room;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by rarnau on 25/10/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class BookingService {

    private static final int MAX_SUITE_ROOMS = 2;
    private static final int MAX_REGULAR_ROOMS = 5;

    private final BookingCalendar bookingCalendar ;
    private final BookingDao bookingDao = BookingDao.getInstance();

    public BookingService() {
        // comrpobar si hay tarifas
        initPrices();
        // comprobar si hay habitaciones
        List<Room> rooms = initRooms();
        bookingCalendar = new BookingCalendar(rooms);
        // bloquear los días ya reservados
        List<Booking> bookings = bookingDao.listAll();
        bookings.forEach(bookingCalendar::lock);
    }

    public Object executeOperation(HotelServerSocket hotelServer, Object dto) {
        HotelOperations operation = HotelOperations.locateOf(dto);
        if (operation != null) {
            switch (operation) {
                case exit:
                    hotelServer.terminate();
                    break;
                case availability:
                    return this.availability((HotelAvailRequest) dto);
                case valuate:
                    return this.valuate((PreBookingRequest) dto);
                case confirmation:
                    return this.confirmation((BookingRequest) dto);
                case cancellation:
                    return this.cancellation((CancellationRequest) dto);
                case bookings:
                    return this.bookings((BookingsRequest) dto);
            }
        }
        return null;
    }

    private List<Room> initRooms() {
        System.out.print("Checking room existences");
        List<Room> list = bookingDao.listAllRooms();
        System.out.print("Found "+list.size()+" rooms");
        if (list.size() == 0) {
            for (int i = 0; i < MAX_SUITE_ROOMS; i++) {
                list.add(new Room(RoomType.SUITE));
            }
            for (int i = 0; i < MAX_REGULAR_ROOMS; i++) {
                list.add(new Room(RoomType.REGULAR));
            }

            bookingDao.saveRooms(list);
            System.out.print("Created "+list.size()+" rooms");
        }
        return list;
    }

    private void initPrices() {
        System.out.print("Checking prices existences");
        List<Price> list = bookingDao.listAllPrices();
        System.out.print("Found "+list.size()+" prices");
        if (list.size() == 0) {
            list.add(new Price(RoomType.SUITE, MealPlan.HALF_PLAN, 200.f));
            list.add(new Price(RoomType.SUITE, MealPlan.FULL_PLAN, 250.f));

            list.add(new Price(RoomType.REGULAR, MealPlan.HALF_PLAN, 50.f));
            list.add(new Price(RoomType.REGULAR, MealPlan.FULL_PLAN, 150.f));

            bookingDao.savePrices(list);
            System.out.print("Created "+list.size()+" prices");
        }
    }

    private PreBookingResponse valuate(PreBookingRequest request) {
        // consultar habitaciones disponibles
        List<Room> rooms = bookingCalendar.availability(request);
        // coger la 1ª libre
        Float price = null;
        Room r = rooms.size() > 0 ? rooms.get(0) : null;
        if (r != null) {
            // calcular el precio de la reserva: nro días x tipo hab x regimen
            price = calcPrice(request);
        }

        PreBookingResponse result = null;
        if (price != null) {
            // construir el objeto de respuesta
            result = new PreBookingResponse();
            result.setFrom(request.getFrom());
            result.setTo(request.getTo());
            result.setMealPlan(request.getMealPlan());
            result.setRoomType(request.getRoomType());
            result.setPrice(price);
        }
        return result;
    }

    private BookingResponse confirmation(BookingRequest request) {
        // consultar habitaciones disponibles
        List<Room> rooms = bookingCalendar.availability(request);
        // coger la 1ª libre
        BookingResponse result = null;
        Room r = rooms.size() > 0 ? rooms.get(0) : null;
        if (r != null) {
            Float price = calcPrice(request);
            Booking booking = Booking.of(request, r, price);
            bookingDao.add(booking);
            bookingCalendar.lock(booking);
            result = new BookingResponse();
            booking.toBookData(result);
        }

        return result;
    }

    private CancellationResponse cancellation(CancellationRequest request) {
        Long bookingId = request.getBookingId();
        Optional<Booking> optBooking = bookingDao.findById(bookingId);
        CancellationResponse result = null;
        if (optBooking.isPresent()) {
            Booking booking = optBooking.get();
            bookingDao.remove(booking);
            bookingCalendar.unlock(booking);
            result = new CancellationResponse();
        }
        return result;
    }

    private BookingsResponse bookings(BookingsRequest request) {
        List<Booking> bookings = bookingDao.list(request);
        BookingsResponse result = new BookingsResponse();
        result.setBookings(bookings.stream()
                .map(Booking::toBookData)
                .collect(Collectors.toList()));
        return result;
    }

    /* métodos privados */

    private Map<RoomType, Map<MealPlan, Float>> getPrices() {
        Map<RoomType, Map<MealPlan, Float>> result = new HashMap<>();
        List<Price> prices = bookingDao.listAllPrices();
        prices.forEach(price -> {
            Map<MealPlan, Float> roomTypePrices = result.computeIfAbsent(price.getRoomType(), rt -> new HashMap<>());
            roomTypePrices.put(price.getMealPlan(), price.getDayPrice());
        });
        return result;
    }

    private RoomAvail getRoomAvail(RoomType rType, List<Room> res, Map<MealPlan, Float> mealPrices) {
        RoomAvail roomAvail = null;
        List<Room> rooms = res.stream().filter(rd -> rd.getRoomType().equals(rType)).collect(Collectors.toList());
        if (rooms.size() > 0) {
            List<RoomPrice> prices = new ArrayList<>();
            roomAvail = new RoomAvail();
            roomAvail.setRoomType(rType);
            roomAvail.setPrices(prices);
            for (MealPlan mealPlan : mealPrices.keySet()) {
                RoomPrice rp1 = new RoomPrice();
                rp1.setDayPrice(mealPrices.get(mealPlan));
                rp1.setMealPlan(mealPlan);
                prices.add(rp1);
            }
        }
        return roomAvail;
    }

    private Float calcPrice(MealPlanData data) {
        RoomType roomType = data.getRoomType();
        MealPlan mealPlan = data.getMealPlan();
        LocalDate from = data.getFrom();
        LocalDate to = data.getTo();

        Map<RoomType, Map<MealPlan, Float>> prices = getPrices();
        Map<MealPlan, Float> mealPlans = prices.get(roomType);
        Float pricePerDay = mealPlans.get(mealPlan);
        int numNights = Period.between(from, to).getDays();
        return pricePerDay * numNights;
    }

    private HotelAvailResponse availability(HotelAvailRequest dto) {
        List<Room> res = bookingCalendar.availability(dto);

        HotelAvailResponse resp = new HotelAvailResponse();
        List<RoomAvail> avail = new ArrayList<>();
        resp.setAvailability(avail);

        Map<RoomType, Map<MealPlan, Float>> prices = getPrices();
        for (RoomType rType : prices.keySet()) {
            Map<MealPlan, Float> mealPrice = prices.get(rType);
            RoomAvail rAvail = getRoomAvail(rType, res, mealPrice);
            avail.add(rAvail);
        }
        return resp;
    }
}
