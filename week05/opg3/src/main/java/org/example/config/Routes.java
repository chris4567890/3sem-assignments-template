package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.apibuilder.EndpointGroup;
import org.example.controller.SecurityController;
import org.example.controller.UserController;
import org.example.daos.HotelDAO;
import org.example.entities.Role;
import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {

    ObjectMapper objectMapper = new ObjectMapper();
    static HotelDAO hotelDAO = new HotelDAO();
    static SecurityController securityController = new SecurityController();
    static UserController userController = new UserController();
    public static EndpointGroup setRoutes(){
        return()-> path("/u",()->{
            post("/registeruser", securityController.register());

        });

    }
    public static EndpointGroup createUserRoute(){
        System.out.println("t");
        return null;

    }

    public static EndpointGroup userLogin(){
        return () -> {
          path("/", () -> {
            securityController.login();

          });
        };
    }

    public static EndpointGroup getRoutes(){
      return () -> {
        path("/", () -> {
            hotelDAO.getAll();
            hotelDAO.getById();
            hotelDAO.create();
            hotelDAO.update();
            hotelDAO.delete();
        });
      };
    }



}
