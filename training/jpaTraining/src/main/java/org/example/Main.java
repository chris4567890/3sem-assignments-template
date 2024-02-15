package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        //HibernateConfig.getEntityManagerFactoryConfig();
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        EntityManager em = emf.createEntityManager();
        Person bob = new Person("Bob","bob@bob.com", Person.Gender.FEMALE);
        em.getTransaction().begin();
        em.persist(bob);
        em.getTransaction().commit();
        em.close();
    }
}