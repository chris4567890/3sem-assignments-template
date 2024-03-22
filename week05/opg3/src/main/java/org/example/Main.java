package org.example;

import io.javalin.Javalin;
import org.example.config.ApplicationConfig;
import org.example.config.Routes;
import org.example.entities.User;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        ApplicationConfig applicationConfig = ApplicationConfig.getInstance();
        applicationConfig.initiateServer().setExceptionHandling().startServer(7007).setRoutes(Routes.setRoutes());


    }
}