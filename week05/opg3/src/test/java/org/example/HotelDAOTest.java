package org.example;

import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.example.config.ApplicationConfig;
import org.example.config.HibernateConfig;
import org.example.daos.HotelDAO;
import org.example.entities.Hotel;
import org.example.entities.Room;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotelDAOTest {
    private static Javalin app;
    private static final String base_url = "http://localhost:7777/api/v1";
    private static HotelDAO hotelDAO;
    private static EntityManagerFactory emfTest;
    private static Hotel h1,h2;
    private static Room r1,r2;

    @BeforeAll
    static void beforeAll(){
        HibernateConfig.setTest(true);
        emfTest = HibernateConfig.getEntityManagerFactory();
        hotelDAO = new HotelDAO();
        app = Javalin.create();
        ApplicationConfig.startServer(app,7777);

    }
    @BeforeEach
    void setup(){
        try(var em = emfTest.createEntityManager()){
            em.getTransaction().begin();
            em.createQuery("DELETE from Room r").executeUpdate();
            em.createQuery("DELETE from Hotel h").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE room_id_seq RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE hotel_id_seq RESTART WITH 1").executeUpdate();
            h1 = new Hotel("hello there","general Kenobi!");
            h2 = new Hotel("the dark side of the force is a pathway to many abilities some might consider unnatural","I am the senate");
            em.persist(h1);
            em.persist(h2);
            em.getTransaction().commit();
        }
    }
    @AfterAll
    static void teardown(){
        HibernateConfig.setTest(false);
        ApplicationConfig.stopServer(app);
    }

    @Test
    void getAll() {
        var em = emfTest.createEntityManager();
        Query query = em.createQuery("select h from Hotel h");
        List<Hotel> hotels = query.getResultList();
        assertNotNull(hotels);
        Hotel foundhotel = hotels.get(1);
        if(hotels !=null){
            assertEquals(foundhotel,hotels.get(1));
        }
    }

    @Test
    void getById() {
        var em = emfTest.createEntityManager();
        Query query = em.createQuery("select h from Hotel h where id = 1");
        Hotel foundhotel = (Hotel) query.getSingleResult();
        if(foundhotel != null){
            assertEquals(h1.getId(),foundhotel.getId());
        }


    }

    @Test
    void create() {
        var em = emfTest.createEntityManager();
        Hotel test_hotel = new Hotel("firefly is overrated","r/unpopularopinion");
        em.getTransaction().begin();
        em.persist(test_hotel);
        em.getTransaction().commit();
        Query query = em.createQuery("select h from Hotel h where h.name = 'firefly is overrated'");
        Hotel foundhotel = (Hotel) query.getSingleResult();
        if(foundhotel != null){
            assertEquals(test_hotel.getName(),foundhotel.getName());
        }

    }

    @Test
    void update() {
        var em = emfTest.createEntityManager();
        h1.setAddress("æøå");
        em.getTransaction().begin();
        em.merge(h1);
        em.getTransaction().commit();
        assertNotEquals("general Kenobi!",h1.getAddress());
    }

    @Test
    void delete() {
        var em = emfTest.createEntityManager();

        em.getTransaction().begin();
        Query getFirstOrder = em.createQuery("select h from Hotel h");
        List<Hotel> firstHotelList = new ArrayList<>();
        em.remove(h1);
        em.getTransaction().commit();
        Query finalList = em.createQuery("select h from Hotel h");
        List<Hotel> finalFinalList = finalList.getResultList();
        assertNotEquals(finalFinalList.size(),firstHotelList.size());
    }
}