package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.io.IOException;

public class PersonDao {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public Person findPerson(Integer id){
        try(EntityManager em = emf.createEntityManager()){
            TypedQuery<Person> query = (TypedQuery<Person>) em.createQuery("select s from Person s where id = :id");
            query.setParameter("id",id);
            Person person1 = query.getSingleResult();

            return person1;
        }

    }
    public boolean deletePerson(int id){
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            //TypedQuery<Person> query = (TypedQuery<Person>) em.createQuery()
            em.remove(findPerson(id));
            em.getTransaction().commit();
            em.close();
            return true;
        }
    }
    public boolean createPerson(Person person){

        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(person);
            person.getId();
            em.getTransaction().commit();
            em.close();
            return true;
        }

    }

    public boolean addFees(int id, Fee fee){
        try(EntityManager em = emf.createEntityManager()){
            if(findPerson(id) != null){
                em.getTransaction().begin();
                Person person = findPerson(id);

                person.addFee(fee);
                em.merge(person);
                em.getTransaction().commit();
                em.close();
                return true;
            }
        }
        return false;
    }

    /*
    public boolean updatePerson(Person person){
        try(EntityManager em = emf.createEntityManager()){
            if(findPerson(person) != null){
                em.getTransaction().begin();
                em.merge(Person.class);
                em.getTransaction().commit();
                em.close();
                return true;
            }
        }
        return false;
    }
*/




}
