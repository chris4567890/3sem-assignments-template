package org.example.daos;

import io.javalin.validation.ValidationException;
import org.example.Role;
import org.example.User;

public interface ISecurityDAO {
    User getVerifiedUser(String username, String password) throws ValidationException;
    User createUser(String username,String password);
    Role createRole(String role);
    User addUserRole(String username,String role);
}
