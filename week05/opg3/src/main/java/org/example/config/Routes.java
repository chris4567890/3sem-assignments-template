package org.example.config;

import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import org.example.HotelDAO;
import org.example.SecurityController;
import org.example.UserController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {
    static HotelDAO hotelDAO = new HotelDAO();
    static SecurityController securityController = new SecurityController();
    static UserController userController = new UserController();
    public static EndpointGroup setRoutes(){
        return ()->{
            path("/",getRoutes());
        };
    }
    //i am never doing this shit crap again ever this task shouldn't be for individuals at all.
    public static EndpointGroup createUser(){
        return()->{
          path("/",()->{
            securityController.login();

          });
        };
    }

    public static EndpointGroup getRoutes(){
      return()->{
        path("/",()->{
            hotelDAO.getAll();
            hotelDAO.getById();
            hotelDAO.create();
            hotelDAO.update();
            hotelDAO.delete();

        });
      };
    }



}
