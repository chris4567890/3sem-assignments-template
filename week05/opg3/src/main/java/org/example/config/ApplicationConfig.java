package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.example.dto.UserDTO;
import org.example.entities.RoleEnum;
//got this from one of my classmates
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.example.controller.SecurityController;
public class ApplicationConfig {

    static ObjectMapper jsonMapper = new ObjectMapper();
    private static ApplicationConfig appConfig;

    private static Javalin app;

    private ApplicationConfig(){}

    public static ApplicationConfig getInstance(){
        if(appConfig == null){
            appConfig = new ApplicationConfig();
        }
        return appConfig;
    }

    public ApplicationConfig initiateServer(){
        app = Javalin.create(config -> {
            config.http.defaultContentType="application/json";
            config.routing.contextPath="/api";

        });
        return appConfig;
    };

    public ApplicationConfig checkSecurityRoles() {
        // Check roles on the user (ctx.attribute("username") and compare with permittedRoles using securityController.authorize()
        app.updateConfig(config -> {

            config.accessManager((handler, ctx, permittedRoles) -> {
                // permitted roles are defined in the last arg to routes: get("/", ctx -> ctx.result("Hello World"), Role.ANYONE);

                Set<String> allowedRoles = permittedRoles.stream().map(role -> role.toString().toUpperCase()).collect(Collectors.toSet());
                if(allowedRoles.contains("ANYONE") || ctx.method().toString().equals("OPTIONS")) {
                    // Allow requests from anyone and OPTIONS requests (preflight in CORS)
                    handler.handle(ctx);
                    return;
                }

                UserDTO user = ctx.attribute("user");
                System.out.println("USER IN CHECK_SEC_ROLES: "+user);
                if(user == null)
                    ctx.status(HttpStatus.FORBIDDEN)
                            .json(jsonMapper.createObjectNode()
                                    .put("msg","Not authorized. No username were added from the token"));

                if (SecurityController.getInstance().authorize(user, allowedRoles))
                    handler.handle(ctx);
                else
                    throw new ApiException(HttpStatus.FORBIDDEN.getCode(), "Unauthorized with roles: "+allowedRoles);
            });
        });
        return appConfig;
    }


    public ApplicationConfig setRoutes(EndpointGroup routes){
        app.routes(routes);
        return appConfig;
    }

    public ApplicationConfig startServer( int port){
        app.start(port);
        return appConfig;
    };

    public ApplicationConfig setExceptionHandling(){
        app.exception(IllegalStateException.class, (e, ctx) -> {
            ObjectNode json = jsonMapper.createObjectNode();
            json.put("errorMessage", e.getMessage());
            ctx.status(500).json(json);
        });
        app.exception(Exception.class, (e, ctx) -> {
            ObjectNode json = jsonMapper.createObjectNode();
            json.put("errorMessage",e.getMessage());
            ctx.status(500).json(json);
        });
        app.error(404, ctx -> {
            ObjectNode json = jsonMapper.createObjectNode();
            json.put("errorMessage", "Not found");
            ctx.status(404).json(json);
        });
        return appConfig;
    }
    public static String getProperty(String propName) throws IOException
    {
        try (InputStream is = HibernateConfig.class.getClassLoader().getResourceAsStream("properties-from-pom.properties"))
        {
            Properties prop = new Properties();
            prop.load(is);
            return prop.getProperty(propName);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException("Could not read property from pom file. Build Maven!");
        }
    }

    public static void startServer(Javalin app, int port) {
        Routes routes = new Routes();
        //app.updateConfig(ApplicationConfig::configuration);
        app.routes(routes.getRoutes());
        //HibernateConfig.setTest(false);
        app.start(port);
    }

    public static void stopServer(Javalin app) {
        app.stop();
    }



}
