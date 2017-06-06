package ua.kyiv.univerpulse.myconnectionpool.controller.impl;

import ua.kyiv.univerpulse.myconnectionpool.controller.Controller;
import ua.kyiv.univerpulse.myconnectionpool.domains.User;
import ua.kyiv.univerpulse.myconnectionpool.service.impl.ServiceFactory;
import ua.kyiv.univerpulse.myconnectionpool.validation.RegExp;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginPostController implements Controller {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("userlogin");
        String password = request.getParameter("userpassword");
        if(!RegExp.isCorrectLogin(login) || !RegExp.isCorrectPassword(password) ){
            request.setAttribute("error","Login incorrect");
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            try {
                rd.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        User user = ServiceFactory.getInstance().getLoginService().loginVerify(login, password);
        if(user == null){
            request.setAttribute("error","Login incorrect!");
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            try {
                rd.forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
        try {
            response.sendRedirect("/profile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
