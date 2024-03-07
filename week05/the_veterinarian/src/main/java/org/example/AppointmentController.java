package org.example;

import io.javalin.*;
import io.javalin.http.Handler;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;


//code from https://www.baeldung.com/javalin-rest-microservices
public class AppointmentController {
    private Javalin app;
    public AppointmentController(Javalin app){
        this.app = app;
    }

    public static Handler getAllAppointments(){
        AppointmentDAO appointmentDAO = AppointmentDAO.instance();
        //Iterable<Appointment> appointments = appointmentDAO.getAppointments();
        List<Appointment> appointments = appointmentDAO.getAppointments();

        return ctx ->{

            ctx.json(appointments);
        };
    }

    public static Handler getAppointmentById(){
        AppointmentDAO appointmentDAO = AppointmentDAO.instance();
        /*path("appointment/",()->{
            get("{id}",ctx->{
                int id = Integer.parseInt(ctx.pathParam("id"));
                ctx.json(appointmentDAO.getAppointmentById(id));
            });
        });*/
        return ctx ->{
            int id = Integer.parseInt(ctx.pathParam("id"));
            Appointment appointment = appointmentDAO.getAppointmentById(id);
            //Optional<Appointment> appointment = Optional.ofNullable(appointmentDAO.getAppointmentById(id));
          if(appointment!= null){
              ctx.json(appointment);
          }else {
              ctx.status(404);
          }
        };
    }



}
