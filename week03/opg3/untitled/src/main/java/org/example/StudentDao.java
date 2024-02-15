package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class StudentDao {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    //persist
    public void save(Student student){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
    }
    //merge and persist
    public Student update(Student student){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        if(findById(student.getId()) != null){
            return  em.merge(student);
        }else{
            return null;
        }
    }
    //remove
    public void delete(int id){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student foundbystudent = em.find(Student.class,id);
        if(foundbystudent != null){
            em.remove(foundbystudent);
            em.getTransaction().commit();
            em.close();
            System.out.println("your student was eliminated a hit squad is now on it's way");
        }
    }
    //finds
    public Student findById(int id){
        EntityManager em = emf.createEntityManager();
        Student studentFoundById = null;
        em.getTransaction().begin();
        studentFoundById = em.find(Student.class,id);
        if(studentFoundById != null){
            return studentFoundById;
        }else{
            return null;
        }
    }

}
