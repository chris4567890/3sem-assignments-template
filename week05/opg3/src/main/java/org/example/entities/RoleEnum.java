package org.example.entities;

import io.javalin.security.RouteRole;

public enum RoleEnum implements RouteRole {

    USER,
    ADMIN,
    ANYONE


}
