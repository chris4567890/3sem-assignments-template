package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

public class ShipmentDao {

   EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();


   public void save_shipment(Shipment shipment){
       EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();
       em.persist(shipment);
       em.getTransaction().commit();
       em.close();
   }
    public void update_destination(Location destination,Integer id){
       EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery("update Shipment s set s.destination = :destination where id = :id");
       query.setParameter("destination",destination);
       query.setParameter("id",id);
       em.getTransaction().commit();
       em.close();
    }
    public void update_source(Location source,Integer id){
       EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery("update Shipment s set s.source = :source where id = :id");
       query.setParameter("source",source);
       query.setParameter("id",id);
       em.getTransaction().commit();
       em.close();
    }

    public void set_package(Package packs,Integer id){
       EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();
       Query query = em.createQuery("update Shipment s set s.packages = :packs where id = :id");
       query.setParameter("packages",packs);
       query.setParameter("id",id);
       em.getTransaction().commit();
       em.close();
    }

}
