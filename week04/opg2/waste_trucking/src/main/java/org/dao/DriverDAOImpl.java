package org.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.config.HibernateConfig;
import org.model.Driver;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DriverDAOImpl implements IDriverDao{
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("truckers");
    @Override
    public String saveDriver(String name, String surname, BigInteger salary) {

        Driver driver = new Driver(name,surname,salary);
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(driver);
            em.getTransaction().commit();
            return driver.getId();
        }
    }

    @Override
    public Driver getDriverById(String id) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Query query = em.createQuery("select d from Driver d where id = :id");
            query.setParameter("id",id);
            Driver driver = (Driver) query.getSingleResult();
            return driver;
        }

    }
    public Driver findDriverById(String id){
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Query query = em.createQuery("select d from Driver d where d.id = :id");
            query.setParameter("id",id);
            return (Driver) query.getSingleResult();
        }
    }
    @Override
    public Driver updateDriver(Driver driver) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Driver founddriver = findDriverById(driver.getId());
            if(founddriver != null){
                em.merge(driver);
                em.getTransaction().commit();
                em.close();
                return driver;

            }else{
                System.out.println("none found");
                return null;
            }

        }
    }

    @Override
    public void deleteDriver(String id) {
        try(EntityManager em = emf.createEntityManager()){
            if(getDriverById(id) != null){
                em.getTransaction().begin();
                Driver driver = getDriverById(id);
                em.remove(driver);
                em.getTransaction().commit();
                em.close();
            }else{
                System.out.println("driver don't exist");
            }
        }
    }

    @Override
    public List<Driver> findAllDriversEmployedAtTheSameYear(String year) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Query query = em.createQuery("select d from Driver d where year(employment_date) = :year ");
            query.setParameter("year",Integer.parseInt(year));

            return query.getResultList();

        }
    }

    @Override
    public List<Driver> fetchAllDriversWithSalaryGreaterThan10000() {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Query query = em.createQuery("select d from Driver d where salary > 10000");

            return query.getResultList();

        }

    }

    @Override
    public BigInteger fetchHighestSalary() {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Query query = em.createQuery("select MAX(d.salary) from Driver d");

            return (BigInteger) query.getSingleResult();
        }

    }

    @Override
    public List<String> fetchFirstNameOfAllDrivers() {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Query query = em.createQuery("select name FROM Driver d");
            return query.getResultList();
        }

    }

    @Override
    public long calculateNumberOfDrivers() {
        ArrayList<Driver> drivers=new ArrayList<>();
        int counter = 0;
        try(EntityManager em = emf.createEntityManager()){

            em.getTransaction().begin();
            Query query = em.createQuery("select d from Driver d");
            drivers = (ArrayList<Driver>) query.getResultList();
            for(Driver d: drivers){
                ++counter;
            }
            return counter;
        }

    }

    @Override
    public Driver fetchDriverWithHighestSalary() {
        Driver driver;
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Query query = em.createQuery("select d from Driver d group by d order by max(d.salary) desc ");
            ArrayList<Driver> drivers = (ArrayList<Driver>) query.getResultList();
            return drivers.get(0);
        }

    }
}
