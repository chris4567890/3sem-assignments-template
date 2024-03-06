package org.example;

import io.javalin.Javalin;
import org.controllers.DogController;

import java.util.*;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {
        public static void main(String[] args) {
                Javalin app = Javalin.create().start(7007);
                //app.get("/", ctx -> ctx.result("Hello World"));
                //app.get("/", ctx -> ctx.result("Hello World"));
                //Map<Integer,Dog> dogMap = Map.of(1,new Dog("Einstien",4),2,new Dog("Mollie",13),3,new Dog("Nicklas",7));
                HashMap<Integer,Dog> dogs = new HashMap<>();
                dogs.put(1,new Dog(1,"Einstien",45));
                dogs.put(2,new Dog(2,"Rowin",10));
                dogs.put(3,new Dog(3,"Mollie",15));
                dogs.put(4,new Dog(4,"Banana",2));
                dogs.put(4,new Dog(4,"Banana",2));
                Dog updatedDog = new Dog(4,"Banana",3);
                DogController dogController = new DogController();
                dogController.getAllDogs(app,dogs);
                dogController.createDog(app,dogs);
                dogController.deleteDog(app,dogs);
                dogController.updateDog(app,dogs);
                dogController.getDogById(app,dogs);
                /*app.routes(()->{
                        path("dogs",()->{
                                get("/",ctx -> ctx.json(dogs));
                                get("/{name}",ctx->{
                                        String name = ctx.pathParam("name");
                                        for(Dog dog : dogs.values()){
                                                if(dog.getName().equals(name)){
                                                        ctx.json(dog);
                                                }
                                        }
                                        /*for(Dog dog: dogMap.values()){
                                                if(dog.getName().equals(name)){
                                                        System.out.println("got to dog");
                                                        ctx.json(dog);
                                                        return;
                                                }
                                        }*/
                                /*});
                                post("/",ctx->{
                                        int idCounter= dogs.size()+1;
                                        Dog incoming = ctx.bodyAsClass(Dog.class);
                                        dogs.put(idCounter,incoming);
                                        ctx.json(incoming);
                                });
                        });
                });*/

        }
}