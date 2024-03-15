package org.example;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;


import org.example.config.HibernateConfig;


//import javax.persistence.Entity;

import java.util.List;

import com.google.gson.Gson;

import static io.javalin.apibuilder.ApiBuilder.*;


public  class HotelDAO extends Dao {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("hotel_db");
    Gson gson = new Gson();

    @Override
    public void getAll() {
        //List<Hotel> hotels = new ArrayList<>();
        EntityManager em = emf.createEntityManager();

         get("/hotel", ctx -> {

            //em.getTransaction().begin();
            Query query = em.createQuery("select h from Hotel h");
            List<Hotel> hotels = query.getResultList();
            ctx.json(hotels);
        });
    }



    @Override
    public void getById() {


            get("/hotel/{id}",ctx -> {
                try(EntityManager em = emf.createEntityManager()) {
                    em.getTransaction().begin();
                    Query query = em.createQuery("select h from Hotel h where h.id =:id");
                    int id = Integer.parseInt(ctx.pathParam("id"));
                    query.setParameter("id", id);
                    Hotel hotel = (Hotel) query.getSingleResult();
                    ctx.json(hotel);
                    em.close();
                }
            });
        }



    @Override
    public void create() {

            post("/hotel",ctx -> {
                try(EntityManager em = emf.createEntityManager()) {
                    em.getTransaction().begin();
                    Hotel hotel = ctx.bodyAsClass(Hotel.class);
                    em.persist(hotel);
                    em.getTransaction().commit();
                    em.close();
                    ctx.json(hotel);
                }
            });
        }


    @Override
    public void update() {

            put("/hotel/{id}",ctx -> {
                try(EntityManager em = emf.createEntityManager()) {
                    em.getTransaction().begin();
                    Query findHotel = em.createQuery("select h from Hotel h where h.id=:id");
                    int id = Integer.parseInt(ctx.pathParam("id"));
                    findHotel.setParameter("id", id);
                    Hotel hotel = (Hotel) findHotel.getSingleResult();
                    if (findHotel != null) {
                        em.merge(hotel);
                        em.getTransaction().commit();
                        em.close();
                        ctx.json(hotel);
                    }
                }
            });
        }


    @Override
    public void delete() {
        put("delete/hotel/{id}",ctx -> {
            try(EntityManager em = emf.createEntityManager()){
                em.getTransaction().begin();
                Query findhotel = em.createQuery("select h from Hotel h where h.id=:id");
                int id = Integer.parseInt(ctx.pathParam("id"));
                Hotel hotel = (Hotel) findhotel.getSingleResult();
                if(findhotel != null){
                    em.remove(hotel);
                    em.getTransaction().commit();
                    em.close();
                    ctx.json("here is your removed hotel: " + hotel);
                }else{
                    ctx.json("object not found");
                }
            }
        });
    }

}

