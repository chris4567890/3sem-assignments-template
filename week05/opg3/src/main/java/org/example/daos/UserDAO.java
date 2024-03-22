package org.example.daos;

import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import jakarta.persistence.Query;
import org.example.config.HibernateConfig;
import org.example.entities.Role;
import org.example.entities.User;
import org.example.controller.UserController;

import java.util.Queue;

public class UserDAO implements ISecurityDAO {
    UserController userController = new UserController();
    @Override
    public User getVerifiedUser(String username, String password) throws ValidationException {
        return userController.getUser(username,password);
    }

    @Override
    public User createUser(String username, String password) {
        return userController.createUser(username,password);
    }



    @Override
    public Role createRole(String role) {
        return null;
    }

    @Override
    public User addUserRole(String username, String role) {
        try(var em = HibernateConfig.getEntityManagerFactory().createEntityManager()){
            em.getTransaction().begin();
            Query queryUser = em.createQuery("select u from users u where u.username =:username");
            queryUser.setParameter("username",username);
            User foundUser = (User) queryUser.getSingleResult();

            if(foundUser != null){
                foundUser.addRole(em.find(Role.class,role));
                em.persist(foundUser);
                em.getTransaction().commit();
                em.close();
                return foundUser;
            }else{
                System.out.println("No user found please try again");
                return null;
            }
        }
    }

    public User register(User saved) {
        try(var em = HibernateConfig.getEntityManagerFactory().createEntityManager()){

            //User user = new User(new5.getUsername(), new5.getPassword(), new5.getRole());
            em.getTransaction().begin();
            em.persist(saved);
            em.getTransaction().commit();

            return saved;
        }
    }
}
