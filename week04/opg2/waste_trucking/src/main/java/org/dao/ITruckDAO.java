package org.dao;

import org.model.Driver;
import org.model.Truck;

import java.util.List;

public interface ITruckDAO {
    int saveTruck(String brand, String registrationNumber, int capacity);
    Truck getTruckById(int id);
    void setTruckAvailable(Truck wasteTruck, boolean available);
    void deleteTruck(int id);
    void addDriverToTruck(Truck wasteTruck, Driver driver);
    void removeDriverFromTruck(Truck wasteTruck, String id);
    List<Truck> getAllAvailableTrucks();
}
