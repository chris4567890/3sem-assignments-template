package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

//code from https://www.baeldung.com/javalin-rest-microservices
public class AppointmentDAO {

    private static List<Appointment> appointments = new ArrayList<>();

    private static AppointmentDAO instance;

    public static AppointmentDAO getInstance(){
        if(instance == null){
            instance = new AppointmentDAO();
        }
        return instance;
    }

    public AppointmentDAO(){

    }

    private static AppointmentDAO appointmentDAO = null;

    public void addAppointments(Appointment appointment){
        appointments.add(appointment);
    }

    static AppointmentDAO instance(){
        if(appointmentDAO == null){
            appointmentDAO = new AppointmentDAO();
        }
        return appointmentDAO;
    }

     Appointment getAppointmentById(Integer id) {
        for(Appointment appointment: appointments){
            if (appointment.getId() == id){
                return appointment;
            }else{
                return null;
            }
        }
        return null;
    }

    public List<Appointment> getAppointments(){
        List<Appointment> appointmentByDate = appointments
                .stream()
                .sorted(Comparator.comparing(Appointment::getDate))
                .collect(Collectors.toList());


        return appointmentByDate;
    }
}
