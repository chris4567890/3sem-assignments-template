package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class NoteDao {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public Note findNote(Note note){
        try(EntityManager em = emf.createEntityManager()) {
            Note foundnote;

            foundnote = em.find(Note.class,note);
            if(foundnote !=null){
                return  foundnote;
            }else {
                return null;
            }
        }
    }





}
