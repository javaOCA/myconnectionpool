package ua.kyiv.univerpulse.myconnectionpool.controller.impl;

import ua.kyiv.univerpulse.myconnectionpool.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexGetController implements Controller {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
