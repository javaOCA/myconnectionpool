package ua.kyiv.univerpulse.myconnectionpool.controller;

import javax.servlet.http.HttpServletRequest;

public interface ControllerFactory {

    Controller getController(HttpServletRequest request);

}
