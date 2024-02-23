package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.example.dao.StudentDAOImpl;

public class Populate {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("school_opg3");
        EntityManager em = emf.createEntityManager();
        Query querySemester = em.createNativeQuery("insert into Semester (name,description)values ('communism','sharing is caring'),('math','1+1!=3') ,('geology','rocks'),('cookie eating','learn to eat cookies')");
        Query queryTeachers = em.createNativeQuery("insert into Teacher (first_name,last_name) values ('patrick','the awesome'),('rolin','the coder'),('nicklas','the waterbender'),('Anders','the not duck'),('Jörg','the jörg'),('thomas','not the train')");
        Query queryStudents = em.createNativeQuery("insert into Student(first_name,last_name) values ('generic','student1'),('generic','student2'),('generic','student3'),('generic','student4'),('generic','student5') ");
        try{
            em.getTransaction().begin();
            querySemester.executeUpdate();
            queryTeachers.executeUpdate();
            queryStudents.executeUpdate();
            em.getTransaction().commit();
        }finally {
            em.close();
        }

    }



}
