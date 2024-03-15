package org.example;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.javalin.apibuilder.ApiBuilder;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Handler;
import io.javalin.validation.ValidationException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jdk.jshell.execution.Util;
import org.example.config.HibernateConfig;

import static io.javalin.apibuilder.ApiBuilder.path;
import static org.apache.http.client.methods.RequestBuilder.post;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;


import org.example.config.HibernateConfig;
import io.javalin.apibuilder.ApiBuilder.*;

//import javax.persistence.Entity;

import java.util.List;

import com.google.gson.Gson;

import static io.javalin.apibuilder.ApiBuilder.*;


public class SecurityController {
    UserDAO userDAO = new UserDAO();
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
   /* public String createToken(UserDTO user) throws ApiException {
        String issuer;
        String token_expire_time;
        String secret_key;
        if(System.getenv("DEPLOYED") != null){
            issuer = System.getenv("ISSUER");
            token_expire_time = System.getenv("TOKEN_EXPIRE_TIME");
            secret_key = System.getenv("SECRET_KEY");
        } else {
            issuer = "shamelessly stolen from rolin";
            token_expire_time = "3000000";
            secret_key = "fuck this fucking string fuck this task fucking fuck fuck fuck";
        }
        return TokenUtils.createToken(user, issuer, token_expire_time, secret_key);
    }

    public class ApiException extends Throwable {
        int statusCode;
        Exception exception;
        public ApiException(int code, String exceptionMessage) {
            statusCode = code;
            exception = new Exception(exceptionMessage);
        }
    }*/



    public void login(){

        ApiBuilder.post("/login",ctx->{
            try(EntityManager em = emf.createEntityManager()){
                em.getTransaction().begin();
                User user = ctx.bodyAsClass(User.class);
                Query query = em.createQuery("select u from users u where u.username =:username and u.password =:password");
                query.setParameter("username",user.getUsername());
                query.setParameter("password",user.getPassword());
                User foundUser = (User) query.getSingleResult();
                ctx.json(foundUser).status(200);
            }
        });
    }



}
