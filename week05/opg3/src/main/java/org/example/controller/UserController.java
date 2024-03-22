package org.example.controller;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.example.config.HibernateConfig;
import org.example.entities.User;

import static io.javalin.apibuilder.ApiBuilder.post;


public class UserController {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    public User getUser(String username, String password){
        var em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("select u from users u where u.username =:username and u.password =:password");
        query.setParameter("username",username);
        query.setParameter("password",password);
        User user = (User) query.getSingleResult();
        return user;
    }

    public User createUser(String username,String password){

        post("/user/create",ctx -> {
            try(var em = emf.createEntityManager()){
                User user = new User(username,password);
                em.getTransaction().begin();
                em.persist(user);
                em.getTransaction().commit();

                ctx.json(user);
            }
        });
        return null;

    }


}
