package org.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.config.HibernateConfig;
import org.model.Driver;
import org.model.Truck;

import java.util.ArrayList;
import java.util.List;

public class TruckDAOImpl implements ITruckDAO {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("truckers");

    @Override
    public int saveTruck(String brand, String registrationNumber, int capacity) {
        Truck truck = new Truck(brand,capacity,registrationNumber);
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(truck);
            em.getTransaction().commit();
            em.close();
        }

        return 0;
    }

    @Override
    public Truck getTruckById(int id) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Query query = em.createQuery("select t from Truck t where id = :id");
            query.setParameter("id",id);
            Truck truck = (Truck) query.getSingleResult();
            return truck;
        }

    }

    @Override
    public void setTruckAvailable(Truck truck, boolean available) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Query query = em.createQuery("select t from Truck t where id = :id");
            query.setParameter("id",truck.getId());
            Truck truck1 = (Truck) query.getSingleResult();
            truck1.set_available(available);
            em.merge(truck1);
            em.getTransaction().commit();
            em.close();
        }
    }

    @Override
    public void deleteTruck(int id) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Query query = em.createQuery("select t from Truck t where id = :id");
            query.setParameter("id",id);
            Truck truck = (Truck) query.getSingleResult();
            em.remove(truck);
            em.getTransaction().commit();
            em.close();
        }
    }

    @Override
    public void addDriverToTruck(Truck truck, Driver driver) {
        List<Driver> drivers = new ArrayList<>();
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Truck foundtruck = em.find(Truck.class,truck.getId());
            Driver founddriver = em.find(Driver.class,driver.getId());
            if(foundtruck != null && founddriver != null){
                drivers.add(driver);
                truck.add_driver(driver);
                driver.add_truck(truck);
                em.merge(truck);
                em.merge(driver);
                em.getTransaction().commit();

            }

        }
    }

    @Override
    public void removeDriverFromTruck(Truck truck, String id) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Truck foundtruck = em.find(Truck.class,truck.getId());
            Query query = em.createQuery("select d from Driver d where id = :id");
            query.setParameter("id",id);
            Driver driver = (Driver) query.getSingleResult();
            if(driver != null){
                driver.remove_truck(foundtruck);
                em.getTransaction().commit();
            }else{
                System.out.println("object not found try again");
            }

        }

    }

    @Override
    public List<Truck> getAllAvailableTrucks() {
        List<Truck> trucks;
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(" select t from Truck t where t.is_available = true");

        return trucks = query.getResultList();

    }
}
