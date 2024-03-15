package org.example;

import io.javalin.validation.ValidationException;

public class UserDAO implements ISecurityDAO{
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
}
