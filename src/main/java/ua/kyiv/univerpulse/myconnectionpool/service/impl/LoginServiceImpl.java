package ua.kyiv.univerpulse.myconnectionpool.service.impl;

import ua.kyiv.univerpulse.myconnectionpool.dao.DaoFactory;
import ua.kyiv.univerpulse.myconnectionpool.dao.utils.exceptions.DaoException;
import ua.kyiv.univerpulse.myconnectionpool.dao.utils.exceptions.ServiceException;
import ua.kyiv.univerpulse.myconnectionpool.domains.User;
import ua.kyiv.univerpulse.myconnectionpool.service.LoginService;

public class LoginServiceImpl implements LoginService {

    @Override
    public User loginVerify(String login, String password) {
        User user;
        try {
            user = DaoFactory.getInstance().getUserDao().getUserByLogin(login);
            if (user == null || !user.getPassword().equals(password)) {
                return null;
            }
        }catch (DaoException e){
            throw new ServiceException(e);
        }
        return user;
    }
}