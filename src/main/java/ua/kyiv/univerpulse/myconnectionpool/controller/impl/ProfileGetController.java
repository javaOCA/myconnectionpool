package ua.kyiv.univerpulse.myconnectionpool.controller.impl;

import ua.kyiv.univerpulse.myconnectionpool.controller.Controller;
import ua.kyiv.univerpulse.myconnectionpool.domains.User;
import ua.kyiv.univerpulse.myconnectionpool.dto.UserDTO;
import ua.kyiv.univerpulse.myconnectionpool.service.impl.ServiceFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProfileGetController implements Controller {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) {
            try {
                response.sendRedirect("/index.jsp");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<UserDTO> usersDTO = ServiceFactory.getInstance().getUserService().getAllUsers();
        request.setAttribute("userdto", usersDTO);
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/profile.jsp");
        try {
            rd.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
