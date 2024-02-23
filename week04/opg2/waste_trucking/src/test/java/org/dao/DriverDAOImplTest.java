package org.dao;

import jakarta.persistence.EntityManagerFactory;
import org.config.HibernateConfig;
import org.junit.jupiter.api.Test;
import org.model.Driver;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class DriverDAOImplTest {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("truckers");
    @Test
    void saveDriver() {
        //Driver driver = new Driver("Nicklas","The Tester", BigInteger.valueOf(-100));
        DriverDAOImpl driverDAO = new DriverDAOImpl();
        assertNotNull(driverDAO.saveDriver("Nicklas","The Tester",BigInteger.valueOf(-100)));

    }

    @Test
    void getDriverById() {
        DriverDAOImpl driverDAO = new DriverDAOImpl();
        assertNotNull(driverDAO.getDriverById("220224-NT-104R"));
    }

}