package org.example;

import io.javalin.Javalin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.eclipse.jetty.io.EndPoint;
import org.example.config.HibernateConfig;
import org.hibernate.engine.query.spi.EntityGraphQueryHint;

import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.*;

public class RoomDAO extends Dao{
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("hotel_db");
    @Override
    public void getAll() {

            get("/room",ctx -> {
                try(EntityManager em = emf.createEntityManager()) {
                    List<Room> rooms = em.createQuery("select r from Room r").getResultList();
                    ctx.json(rooms);
                }
            });

    }

    @Override
    public void getById() {
        try(EntityManager em = emf.createEntityManager()){
            get("/room/{id}",ctx -> {
                em.getTransaction().begin();
                int id = Integer.parseInt(ctx.pathParam("id"));
                Room room = em.find(Room.class,id);
                ctx.json(room);
            });

        }
    }

    @Override
    public void create() {
        try(EntityManager em = emf.createEntityManager()){
            post("/room",ctx -> {
               em.getTransaction().begin();
               Room room = ctx.bodyAsClass(Room.class);
               em.persist(room);
               em.getTransaction().commit();
               em.close();
            });
        }
    }

    @Override
    public void update() {
        try(EntityManager em = emf.createEntityManager()){
            put("/room/{id}",ctx -> {
               em.getTransaction().begin();
               int id = Integer.parseInt(ctx.pathParam("id"));
               Room room = em.find(Room.class,id);
               em.merge(room);
               em.getTransaction().commit();
               em.close();
            });
        }
    }

    @Override
    public void delete() {

            /*delete("/room/{id}",ctx -> {
                try(EntityManager em = emf.createEntityManager()){
               em.getTransaction().begin();
               int id = Integer.parseInt(ctx.par);
               Room room = em.find(Room.class,id);
               em.remove(room);
               em.getTransaction().commit();
               em.close();}
            });*/
            }


}
