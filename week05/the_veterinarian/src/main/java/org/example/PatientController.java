package org.example;

import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;

public class PatientController {

    private Javalin app;

    public PatientController(Javalin app){
        this.app = app;
    }
    public static Handler getAllPatients(){
        PatientDAO patientDAO = PatientDAO.instance();
        List<Patient> patients = patientDAO.getPatients();
        return ctx->{
          ctx.json(patients);
        };
    }
    public static Handler getPatientById(){
        PatientDAO patientDAO = PatientDAO.instance();
        return ctx->{
          int id = Integer.parseInt(ctx.pathParam("id"));
          Patient patient = PatientDAO.getPatientById(id);
          if(patient!=null){
              ctx.json(patient);
          }else{
              ctx.status(404);
          }
        };
    }

}
