package ua.kyiv.univerpulse.myconnectionpool.controller.impl;

import ua.kyiv.univerpulse.myconnectionpool.controller.Controller;
import ua.kyiv.univerpulse.myconnectionpool.controller.ControllerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ControllerPostFactory implements ControllerFactory {

    private static ControllerFactory factory = new ControllerPostFactory();
    private Map<String,Controller> controllersMap = new HashMap<>();

    private ControllerPostFactory(){
        controllersMap.put("login", new LoginPostController());
        controllersMap.put("registration", new RegistrationPostController());
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