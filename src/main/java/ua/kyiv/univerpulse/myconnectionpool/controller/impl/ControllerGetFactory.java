package ua.kyiv.univerpulse.myconnectionpool.controller.impl;

import ua.kyiv.univerpulse.myconnectionpool.controller.Controller;
import ua.kyiv.univerpulse.myconnectionpool.controller.ControllerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ControllerGetFactory implements ControllerFactory {

    private static ControllerFactory factory = new ControllerGetFactory();
    private Map<String, Controller> controllersMap = new HashMap<>();

    private ControllerGetFactory(){
        controllersMap.put("registration", new RegistrationGetController());
        controllersMap.put("login", new LoginGetController());
        controllersMap.put(null, new IndexGetController());
        controllersMap.put("profile", new ProfileGetController());
        controllersMap.put("logout", new LogoutGetController());
    }

    public Controller getController(HttpServletRequest request){
        String[] req = request.getRequestURI().split("/");
        if (req.length == 0){
            return controllersMap.get(null);
        }
        return controllersMap.get(req[req.length - 1]);
    }

    public static ControllerFactory getInstance(){
        return factory;
    }

}