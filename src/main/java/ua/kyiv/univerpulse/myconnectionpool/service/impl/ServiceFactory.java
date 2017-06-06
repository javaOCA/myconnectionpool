package ua.kyiv.univerpulse.myconnectionpool.service.impl;

import ua.kyiv.univerpulse.myconnectionpool.service.LoginService;
import ua.kyiv.univerpulse.myconnectionpool.service.RegistrationService;
import ua.kyiv.univerpulse.myconnectionpool.service.UserService;

public class ServiceFactory {

    private static ServiceFactory instance = new ServiceFactory();

    private UserService userService = new UserServiceImpl();
    private LoginService loginService = new LoginServiceImpl();
    private RegistrationService registrationService = new RegistrationServiceImpl();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public RegistrationService getRegistrationService() {
        return registrationService;
    }
}
