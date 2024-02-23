package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

public class LocationDAO {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    public void save_the_object(Location location){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(location);
        em.getTransaction().commit();
        em.close();
    }

    public Location find_by_id(Integer id){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Location location = em.find(Location.class,id);
        return location;
    }

    public void update_location(double longtitude,double latitude,Integer id){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query queryLat = em.createQuery("update Location l set latitude = :latitude where id = :id");
        queryLat.setParameter("latitude",latitude);
        queryLat.setParameter("id",id);
        Query queryLong = em.createQuery("update Location l set longtitude = longtitude where id = :id");
        queryLong.setParameter("longtitude",longtitude);
        queryLong.setParameter("id",id);
        em.getTransaction().commit();
        em.close();

    }

}
