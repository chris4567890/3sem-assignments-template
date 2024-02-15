package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.hibernate.engine.query.spi.EntityGraphQueryHint;

import java.io.IOException;
import java.util.List;

public class UnicornDao {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    public void save(Unicorn unicorn){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(unicorn);
        em.getTransaction().commit();
        em.close();
    }

    public List<Unicorn> findAll(){

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        var search = em.createQuery("select u from Unicorn u  ");
        return search.getResultList();

    }

    public Unicorn update(Unicorn unicorn){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        if(findById(unicorn.getId()) != null){
            return em.merge(unicorn);
        }else{
            return null;
        }


    }

    public void delete(int id){
        EntityManager em = emf.createEntityManager();
        Unicorn unicornFound = null;
        em.getTransaction().begin();
        unicornFound = findById(id);
        if(unicornFound != null){
            em.remove(unicornFound);
            em.getTransaction().commit();
            em.close();
            System.out.println("Succesfully Deleted");
        }else{
            System.out.println("your unicorn does not exist or you typed the wrong id");
        }
    }

    public Unicorn findById(int id){
        EntityManager em = emf.createEntityManager();
        Unicorn unicornFoundById = null;
        em.getTransaction().begin();
        unicornFoundById = em.find(Unicorn.class,id);
        if(unicornFoundById != null){
            return unicornFoundById;
        }else {
            return null;
        }

    }



}
