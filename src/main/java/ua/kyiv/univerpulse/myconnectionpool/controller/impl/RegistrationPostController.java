package ua.kyiv.univerpulse.myconnectionpool.controller.impl;

import ua.kyiv.univerpulse.myconnectionpool.controller.Controller;
import ua.kyiv.univerpulse.myconnectionpool.dao.utils.exceptions.ServiceException;
import ua.kyiv.univerpulse.myconnectionpool.dao.utils.exceptions.UserExistException;
import ua.kyiv.univerpulse.myconnectionpool.dto.UserDTO;
import ua.kyiv.univerpulse.myconnectionpool.service.impl.ServiceFactory;
import ua.kyiv.univerpulse.myconnectionpool.validation.RegExp;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationPostController implements Controller {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("username");
        String login = request.getParameter("userlogin");
        String password = request.getParameter("userpassword");
        if(!RegExp.isCorrectName(name) || !RegExp.isCorrectLogin(login) || !RegExp.isCorrectPassword(password)){
            request.setAttribute("error","Credentials incorrect");
            RequestDispatcher rd = request.getRequestDispatcher("/registration.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            UserDTO userDTO = new UserDTO(name, login, password);
            try {
                ServiceFactory.getInstance().getRegistrationService().saveUser(userDTO);
            } catch (UserExistException ex) {
                request.setAttribute("error", "User already exist!");
                RequestDispatcher rd = request.getRequestDispatcher("/registration.jsp");
                try {
                    rd.forward(request, response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (ServiceException ex) {
                ///exception on dao side
            }
            try {
                response.sendRedirect("/index.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
