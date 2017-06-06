package ua.kyiv.univerpulse.myconnectionpool.service;

import ua.kyiv.univerpulse.myconnectionpool.domains.User;

public interface LoginService {

    User loginVerify(String login, String password);

}
