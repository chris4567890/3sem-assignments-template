package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        //EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        StudentDao studentDao = new StudentDao();
        //persist
        Student karlMarx = new Student("karl","max","email@email.share", 202);
        studentDao.save(karlMarx);
    }
}