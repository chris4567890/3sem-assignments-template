package org.example;

import io.javalin.Javalin;
import io.javalin.Javalin.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.ApplicationConfig;
import org.example.config.HibernateConfig;
import org.example.config.Routes;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
       // ApplicationConfig applicationConfig = ApplicationConfig.getInstance();
       // applicationConfig.initiateServer().setExceptionHandling().startServer(7007).setRoutes(Routes.setRoutes());
        //Javalin app = Javalin.create().start(7010);

        User user = new User("Thomas","123","admin");
        System.out.println(user.getPassword());

    }
}