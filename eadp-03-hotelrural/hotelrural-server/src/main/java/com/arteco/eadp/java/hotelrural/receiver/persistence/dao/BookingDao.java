package com.arteco.eadp.java.hotelrural.receiver.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BookingDao {
    private final EntityManager entityManager;

    private static final BookingDao instance = new BookingDao();

    private BookingDao() {
        EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("hotelRuralPU");
        this.entityManager = sessionFactory.createEntityManager();
    }
}
