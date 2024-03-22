package org.example.daos;

import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import org.example.config.HibernateConfig;
import org.example.entities.Role;
import org.example.entities.User;
import org.example.controller.UserController;

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
        return null;
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
