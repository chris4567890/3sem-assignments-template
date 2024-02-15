package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PointDao {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    //start point must be lower than endpoint but higher than the max amount in the db
    public void storePoints(int startPoint,int endPoint ){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        for(int i = startPoint; i < endPoint; ++i){
            Point point = new Point(i,i);
            em.persist(point);
        }
        em.getTransaction().commit();
    }
    public void findTheNumberOfPoints(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("Select count(p) from Point p");
        System.out.println("amount :"+q.getSingleResult());
    }

    public void findAverageXAmount(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("select avg(p.x)from Point p");
        System.out.println(q.getSingleResult());
    }

    public void getAllThePoints(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Point> query = em.createQuery("select p from Point p", Point.class);
        List<Point> points = query.getResultList();
        for(Point p : points){
            System.out.println(p.toString());
        }
    }



}
