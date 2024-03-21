package org.example.daos;

import io.javalin.Javalin;
import org.eclipse.jetty.io.EndPoint;

public interface IDAO<T> {

    public void getAll();
    public void getById();
    public void create();
    public void update();
    public void delete();


}
