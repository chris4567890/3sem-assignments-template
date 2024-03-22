package org.example.daos;

import io.javalin.validation.ValidationException;
import org.example.entities.Role;
import org.example.entities.User;

public interface ISecurityDAO {
    User getVerifiedUser(String username, String password) throws ValidationException;
    User createUser(String username,String password);
    Role createRole(String role);
    User addUserRole(String username,String role);
}
