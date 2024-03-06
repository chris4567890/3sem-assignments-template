package org.example;

import io.javalin.Javalin;

public interface IDAO<T> {

    public void getAll(Javalin app);
    public void getById(Javalin app);
    public void create(Javalin app);
    public void update(Javalin app);
    public void delete(Javalin app);


}
