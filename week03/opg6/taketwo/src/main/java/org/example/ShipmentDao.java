package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ShipmentDao {

   EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();


   public void save_shipment(Shipment shipment){
       EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();
       em.persist(shipment);
       em.getTransaction().commit();
       em.close();
   }




}
