package com.arteco.eadp.java.hotelrural.receiver.persistence.dao;

import com.arteco.eadp.java.hotelrural.common.dto.base.Dated;
import com.arteco.eadp.java.hotelrural.receiver.persistence.model.*;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class BookingDao {
    private static final BookingDao instance = new BookingDao();
    private final EntityManager entityManager;

    private BookingDao() {
        EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("hotelRuralPU");
        this.entityManager = sessionFactory.createEntityManager();
    }

    public static BookingDao getInstance() {
        return instance;
    }

    public List<Booking> list(Dated request) {
        QBooking booking = QBooking.booking;
        JPAQuery<Booking> query = new JPAQuery<>(entityManager);
        return query.from(booking)
                .where(request.getFrom() != null ? booking.from.after(request.getFrom()) : booking.id.isNotNull())
                .where(request.getTo() != null ? booking.from.before(request.getTo()) : booking.id.isNotNull())
                .orderBy(booking.from.asc())
                .fetch();
    }

    public Optional<Booking> findById(Long bookingId) {
        Booking booking = entityManager.find(Booking.class, bookingId);
        return Optional.ofNullable(booking);
    }

    public void remove(Booking booking) {
        entityManager.remove(booking);
    }

    public void add(Booking booking) {
        entityManager.persist(booking);
    }

    public List<Booking> listAll() {
        QBooking booking = QBooking.booking;
        JPAQuery<Booking> query = new JPAQuery<>(entityManager);
        return query.from(booking)
                .orderBy(booking.from.asc())
                .fetch();
    }

    public List<Price> listAllPrices() {
        QPrice price = QPrice.price;
        JPAQuery<Price> query = new JPAQuery<>(entityManager);
        return query.from(price)
                .orderBy(price.id.asc())
                .fetch();
    }

    public void savePrices(List<Price> list) {
        for (Price price : list) {
            entityManager.persist(price);
        }
    }

    public List<Room> listAllRooms() {
        QRoom room = QRoom.room;
        JPAQuery<Room> query = new JPAQuery<>(entityManager);
        return query.from(room)
                .orderBy(room.id.asc())
                .fetch();
    }

    public void saveRooms(List<Room> list) {
        for (Room room : list) {
            entityManager.persist(room);
        }
    }
}
