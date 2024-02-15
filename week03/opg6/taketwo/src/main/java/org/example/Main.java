package org.example;

import jakarta.persistence.EntityManagerFactory;



public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        //EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

        Package pac = new Package("5987683475","Patrick","Nicklas",Delivery_status.TRANSIT);
        PackageDao packageManager = new PackageDao();
        //packageManager.save_the_object(pac);
        //System.out.println(packageManager.find_by_tracking_number("'5987683475'"));
        //System.out.println(pac.getTracking_number());
        packageManager.update_status("'5987683475'",Delivery_status.RECEIVED);

    }
}