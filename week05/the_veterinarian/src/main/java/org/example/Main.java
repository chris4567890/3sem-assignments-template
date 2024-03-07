package org.example;

import io.javalin.Javalin;

import java.util.Date;
import java.util.HashMap;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7071);
        Date date = new Date(2000,11,21);
        Appointment appointment1 = new Appointment(1,date,"Robin");
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        appointmentDAO.addAppointments(appointment1);
        AppointmentController appointmentController = new AppointmentController(app);
        app.routes(()->{
            path("api/vet/",()->{
               get("appointment/{id}",appointmentController.getAppointmentById());
            });
        });

        appointmentController.getAllAppointments();
    }
}