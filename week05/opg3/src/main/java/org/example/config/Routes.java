package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.apibuilder.EndpointGroup;
import org.example.*;
import org.example.daos.HotelDAO;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {
    ObjectMapper objectMapper = new ObjectMapper();
    static HotelDAO hotelDAO = new HotelDAO();
    static SecurityController securityController = new SecurityController();
    static UserController userController = new UserController();
    public static EndpointGroup setRoutes(){
        return ()->{
            path("/",getRoutes());
        };
    }


    public static EndpointGroup UserRoutes(){
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
