package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

public class PackageDao {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public PackageDao(){

    }
    public void save_the_object(Package pac){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pac);
        em.getTransaction().commit();
        em.close();
    }
    public Boolean update_status(String tracking_number,Delivery_status status){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        if(find_by_tracking_number(tracking_number) != null){
            Query query = em.createQuery("update Package pac SET pac.delivery_status = :status where pac.tracking_number = :tracking_number");
            //based on code from here https://docs.oracle.com/javaee/6/tutorial/doc/bnbrg.html
            query.setParameter("status",status);
            query.setParameter("tracking_number",tracking_number);
            query.executeUpdate();
            em.getTransaction().commit();
            em.close();
            return true;
        }else{
            return false;
        }
    }
    public Package find_by_tracking_number(String tracking_number){
        EntityManager em = emf.createEntityManager();
        Package pack = null;
        em.getTransaction().begin();

        Query query = em.createQuery("select pack from Package pack where tracking_number =" +tracking_number, Package.class);

        pack = (Package) query.getSingleResult();

        if(pack != null){
            return pack;
        }else{
            return null;
        }

    }

}
