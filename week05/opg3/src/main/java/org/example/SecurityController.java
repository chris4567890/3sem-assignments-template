package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import io.javalin.apibuilder.ApiBuilder;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import io.javalin.validation.ValidationException;
import jakarta.persistence.*;
import jdk.jshell.execution.Util;
import org.example.config.ApiException;
import org.example.config.HibernateConfig;

import static io.javalin.apibuilder.ApiBuilder.path;
import static org.apache.http.client.methods.RequestBuilder.post;

import jakarta.persistence.EntityManagerFactory;


import org.example.config.HibernateConfig;
import io.javalin.apibuilder.ApiBuilder.*;

//import javax.persistence.Entity;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import static io.javalin.apibuilder.ApiBuilder.*;
//import static org.example.config.Routes.userController;


public class SecurityController {
    UserDAO userDAO = new UserDAO();
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

    ObjectMapper objectMapper = new ObjectMapper();
    public Handler register(){
        return(ctx)->{
            ObjectNode returnObject = objectMapper.createObjectNode();
            try{
                UserDTO userinput = ctx.bodyAsClass(UserDTO.class);
                User created = userDAO.createUser(userinput.getUser().getUsername(),userinput.getUser().getPassword());
                String token = createToken(new UserDTO(created));
                ctx.status(HttpStatus.CREATED).json(new TokenDTO(token,userinput.getUser().getUsername()));
            }catch(EntityExistsException e)
            {

            }
        };
    }
    public String createToken(UserDTO user, String ISSUER, String TOKEN_EXPIRE_TIME, String SECRET_KEY){
        // https://codecurated.com/blog/introduction-to-jwt-jws-jwe-jwa-jwk/
        try {
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUser().getUsername())
                    .issuer(ISSUER)
                    .claim("username", user.getUser().getRole())
                    .claim("roles", user.getUser().getRolesAsStrings().stream().reduce("",(s1, s2) -> s1 + "," + s2))
                    .expirationTime(new Date(new Date().getTime() + Integer.parseInt(TOKEN_EXPIRE_TIME)))
                    .build();
            Payload payload = new Payload(claimsSet.toJSONObject());

            JWSSigner signer = new MACSigner(SECRET_KEY);
            JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
            JWSObject jwsObject = new JWSObject(jwsHeader, payload);
            jwsObject.sign(signer);
            return jwsObject.serialize();

        } catch (JOSEException e) {
            e.printStackTrace();
            throw new ApiException(500, "Could not create token");
        }
    }
    //from thomases example code
    public String createToken(UserDTO user) {
        String ISSUER;
        String TOKEN_EXPIRE_TIME;
        String SECRET_KEY;

        if (System.getenv("DEPLOYED") != null) {
            ISSUER = System.getenv("ISSUER");
            TOKEN_EXPIRE_TIME = System.getenv("TOKEN_EXPIRE_TIME");
            SECRET_KEY = System.getenv("SECRET_KEY");
        } else {
            ISSUER = "Thomas Hartmann";
            TOKEN_EXPIRE_TIME = "1800000"; // 30 minutes in milliseconds
            SECRET_KEY = "this is a secret key that's totally a secret and shouldn't be revealed never ever ever ever";
        }
        return createToken(user, ISSUER, TOKEN_EXPIRE_TIME, SECRET_KEY);
    }

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
