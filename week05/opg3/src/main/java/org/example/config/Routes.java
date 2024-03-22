package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.apibuilder.EndpointGroup;
import org.example.controller.SecurityController;
import org.example.controller.UserController;
import org.example.daos.HotelDAO;
import org.example.daos.UserDAO;
import org.example.entities.Role;
import org.example.entities.RoleEnum;

import static io.javalin.apibuilder.ApiBuilder.*;
import static org.example.config.ApplicationConfig.jsonMapper;

public class Routes {

    ObjectMapper objectMapper = new ObjectMapper();
    static HotelDAO hotelDAO = new HotelDAO();
    static UserDAO userDAO = new UserDAO();

    static SecurityController securityController = new SecurityController();
    static UserController userController = new UserController();

    public static EndpointGroup getSecuredRoutes(){
        return ()->{
          path("/protected",()->{
              before(securityController.authenticate());
              get("/user_demo",(ctx)->ctx.json(jsonMapper.createObjectNode().put("msg","Hello from USER Protected")),RoleEnum.USER);
              get("/admin_demo",(ctx)->ctx.json(jsonMapper.createObjectNode().put("msg","Hello from ADMIN Protected")),RoleEnum.ADMIN);
          });
        };
    }

    public static EndpointGroup setRoutes(){
        return()-> path("/u",()->{
            post("/registeruser", securityController.register());
            post("/loginuser",securityController.login(), RoleEnum.ANYONE);
            put("/addrole/{role}/{id}", securityController.addRole());

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
