package org.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PrePersist;
import org.config.HibernateConfig;
import org.junit.jupiter.api.Test;
import org.model.Truck;

import static org.junit.jupiter.api.Assertions.*;

class TruckDAOImplTest {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("truckers");



    @Test
    void saveTruck() {
        TruckDAOImpl truckDAO = new TruckDAOImpl();
        int result = truckDAO.saveTruck("Volkswagen","4949-aaa-ø",9837598);
        assertEquals(0,result);
    }

    @Test
    void getTruckById() {
        TruckDAOImpl truckDAO = new TruckDAOImpl();

        assertNotNull(truckDAO.getTruckById(3));
    }



    @Test
    void deleteTruck() {
    }

    @Test
    void addDriverToTruck() {
    }

    @Test
    void removeDriverFromTruck() {
    }

    @Test
    void getAllAvailableTrucks() {
    }
}