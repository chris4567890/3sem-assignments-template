package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class FeeDao {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    public List findAllFeeBelongingToThisPerson(int user_id){
        try(EntityManager em = emf.createEntityManager()){
            List<Fee> fees = new ArrayList<>();
            TypedQuery<Fee> query = (TypedQuery<Fee>) em.createQuery("SELECT f FROM Fee f WHERE f.person.id = :user_id");
            query.setParameter("user_id",user_id);
            fees.addAll(query.getResultList());
            return fees;
        }

    }
    public Fee findFee(int id){
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            //Fee foundFee = em.find(Fee.class, fee.getId());
            TypedQuery<Fee> query = (TypedQuery<Fee>) em.createQuery("select f from Fee f where id = :id");
            query.setParameter("id",id);
            Fee foundFee = query.getSingleResult();
            return foundFee;
        }
    }

    /*public boolean removeFee(Fee fee){
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            if(findFee(fee) != null){
                fee = em.find(Fee.class,fee.getId());
                em.remove(fee);
                em.getTransaction().commit();
                em.close();
                return true;
            }
        }
        return false;
    }



    public boolean updateFee(Fee fee){
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            if(findFee(fee) != null){
                em.merge(fee);
                em.getTransaction().commit();

            }else{
                return false;
            }
        }
        return false;
    }
*/
}
