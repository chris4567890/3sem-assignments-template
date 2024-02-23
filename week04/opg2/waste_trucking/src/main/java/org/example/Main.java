package org.example;

import jakarta.persistence.EntityManagerFactory;
import org.config.HibernateConfig;
import org.dao.DriverDAOImpl;
import org.dao.IDriverDao;
import org.dao.TruckDAOImpl;
import org.model.Driver;
import org.model.Truck;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("truckers");
        /*Date hired_date1 = new Date(2024-1900,01,01);
        BigInteger low_salery = BigInteger.valueOf(3579);
        Driver driver = new Driver("Patrick","Patrickson",low_salery);
        driver.setEmployment_date(hired_date1);
        driver.generate_id();
        System.out.println(driver.getId());*/
        Date hired_date1 = new Date(2024-1900,06,04);

        DriverDAOImpl driverDAO = new DriverDAOImpl();
        Driver driver = driverDAO.getDriverById("220224-PA-353E");
        //String id = driverDAO.saveDriver("Patrick","Awesome",BigInteger.valueOf(597437));
        //Driver foundDriver = driverDAO.getDriverById("220224-PA-353E");
        //foundDriver.setSalary(BigInteger.valueOf(400));
        //driverDAO.updateDriver(foundDriver);
        //driverDAO.deleteDriver("220224-PA-799E");
        /*List<Driver> driversWithSameYear = driverDAO.findAllDriversEmployedAtTheSameYear("2024");
        for(Driver d:driversWithSameYear){
            System.out.println(d);
        }*/
        /*List<Driver> drivers = driverDAO.fetchAllDriversWithSalaryGreaterThan10000();
        for(Driver d:drivers){
            System.out.println(d);
        }*/
        //System.out.println(driverDAO.fetchHighestSalary());
        /*List<String> names = driverDAO.fetchFirstNameOfAllDrivers();
        for(String s : names){
            System.out.println(s);
        }*/
        //System.out.println(driverDAO.fetchDriverWithHighestSalary());
        TruckDAOImpl truckDAO = new TruckDAOImpl();
        Truck truck = truckDAO.getTruckById(2);
        //truckDAO.saveTruck("tesla","45-44-aaa",97543);

        //truckDAO.addDriverToTruck(truck,driver);
        //boolean available = false;
        //Truck truck = truckDAO.getTruckById(1);
        /*truckDAO.setTruckAvailable(truck,available);
        System.out.println(truckDAO.getTruckById(1));*/
        //truckDAO.deleteTruck(1);
        //truckDAO.removeDriverFromTruck(truck, driver.getId());
        //truckDAO.deleteTruck(2);
    }
}