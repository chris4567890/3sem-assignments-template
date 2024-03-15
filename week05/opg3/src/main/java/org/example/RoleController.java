package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.example.config.HibernateConfig;

public class RoleController {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    public Role createRole(String role){
        EntityManager em = emf.createEntityManager();
        Role roleCreate = new Role(role);
        em.getTransaction().begin();
        em.persist(roleCreate);
        em.getTransaction().commit();
        return roleCreate;
    }

    public Role deleteRole(String role){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Role role1 = em.find(Role.class,role);
        em.remove(role1);
        em.getTransaction().commit();
        return role1;
    }


}
