package ua.kyiv.univerpulse.myconnectionpool.controller.impl;

import ua.kyiv.univerpulse.myconnectionpool.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Alexey on 14.05.2017.
 */
public class LogoutGetController implements Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if(session!=null){
            session.invalidate();
        }
        try {
            response.sendRedirect("/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
