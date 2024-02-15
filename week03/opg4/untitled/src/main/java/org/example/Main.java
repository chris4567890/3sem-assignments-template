package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        EntityManager em = emf.createEntityManager();

        PointDao pointDao = new PointDao();
        //pointDao.storePoints(1001,3000);
        //pointDao.findAverageXAmount();
        //pointDao.getAllThePoints();
        //pointDao.findTheNumberOfPoints();
        // Close the database connection:
        em.close();
        emf.close();
    }

}