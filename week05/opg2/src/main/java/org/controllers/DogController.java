package org.controllers;

import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import org.example.Dog;

import java.nio.file.Paths;
import java.util.HashMap;

import static io.javalin.apibuilder.ApiBuilder.*;

public class DogController {

    public void getAllDogs(Javalin app, HashMap<Integer, Dog> dogs) {
        app.routes(() -> {
            path("dogs", () -> {
                get("/", ctx -> ctx.json(dogs));
            });
        });
    }

    public void getDogById(Javalin app, HashMap<Integer, Dog> dogs) {
        app.routes(() -> {
            path("dogs", () -> {
                get("/{id}", ctx -> {
                    int searchId = Integer.valueOf(ctx.pathParam("id"));
                    for(Dog dog :dogs.values()){
                        if(dog.getId() ==searchId){
                            ctx.json(dog);
                        }else{
                            ctx.status(404);
                        }

                    }
                });
            });
        });
    }

    public void createDog(Javalin app, HashMap<Integer, Dog> dogs) {
        app.routes(() -> {
            path("dogs", () -> {
                post("/", ctx -> {
                    int idCounter = dogs.size() + 1;
                    Dog incoming = ctx.bodyAsClass(Dog.class);
                    dogs.put(idCounter, incoming);
                    ctx.status(HttpStatus.OK).json(incoming);
                });
            });
        });
    }

    public void updateDog(Javalin app,HashMap<Integer, Dog> dogs){
        app.routes(()->{
            path("dogs",()->{
                put("/{id}",ctx->{
                    int id = Integer.valueOf(ctx.pathParam("id"));
                    Dog incoming = ctx.bodyAsClass(Dog.class);
                    if( dogs.containsKey(id)){
                        dogs.put(id,incoming);
                        ctx.status(HttpStatus.OK).json(incoming);
                    }else{
                        ctx.status(404).result("Nothing was found fight for democracy!");
                    }
                });
            });
        });
    }

    public void deleteDog(Javalin app,HashMap<Integer,Dog> dogs){
        app.routes(()->{
            path("dogs",()->{
                delete("/{id}",ctx->{
                int id = Integer.valueOf(ctx.pathParam("id"));
                if(dogs.containsKey(id)){
                    dogs.remove(id);
                    ctx.status(HttpStatus.OK).json(dogs);
                }else{
                    ctx.status(404).result("scooby dooby do where are you?");
                }
                });
            });
        });
    }

    /*public HashMap<Integer,Dog> deleteDog( HashMap<Integer,Dog> dogs, int id){
        dogs = dogs;
        if(dogs.containsKey(id)){
            dogs.remove(dogs);
            return dogs;
        }else{
            return null;
        }
    }*/

}
