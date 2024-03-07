package org.example;

import io.javalin.Javalin;

import java.util.Date;
import java.util.HashMap;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7071);
        Date date = new Date(2001,9,11);
        Appointment appointment1 = new Appointment(1,date.toString(),"Robin");
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        appointmentDAO.addAppointments(appointment1);
        AppointmentController appointmentController = new AppointmentController(app);
        Patient patient = new Patient(1,"Patrick","ged",4);
        PatientDAO patientDAO = new PatientDAO();
        PatientController patientController = new PatientController(app);
        PatientDAO.addPatient(patient);

        app.routes(()->{
            path("api/vet/",()->{
               get("appointment/{id}",appointmentController.getAppointmentById());
               get("appointment",AppointmentController.getAllAppointments());
               get("patient/{id}",PatientController.getPatientById());
               get("patient",PatientController.getAllPatients());
            });
        });

        //appointmentController.getAllAppointments();
    }
}