package org.example;

import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    private static List<Patient> patients = new ArrayList<>();

    private static PatientDAO instance = null;

    public static PatientDAO instance(){
        if(instance == null){
            instance = new PatientDAO();
        }
        return instance;
    }

    public static void addPatient(Patient patient){
        patients.add(patient);
    }

    public List<Patient> getPatients(){
        return patients;
    }

    public static Patient getPatientById(Integer id){
        for(Patient patient: patients){
            if(patient.getId() == id){
                return patient;
            }else{
                return null;
            }
        }
        return null;
    }



}
