package ua.kyiv.univerpulse.myconnectionpool.service.impl;

import ua.kyiv.univerpulse.myconnectionpool.dao.DaoFactory;
import ua.kyiv.univerpulse.myconnectionpool.dao.utils.TransactionHelper;
import ua.kyiv.univerpulse.myconnectionpool.dao.utils.exceptions.DaoException;
import ua.kyiv.univerpulse.myconnectionpool.dao.utils.exceptions.ServiceException;
import ua.kyiv.univerpulse.myconnectionpool.dao.utils.exceptions.UserAlreadyExistException;
import ua.kyiv.univerpulse.myconnectionpool.dao.utils.exceptions.UserExistException;
import ua.kyiv.univerpulse.myconnectionpool.domains.User;
import ua.kyiv.univerpulse.myconnectionpool.dto.UserDTO;
import ua.kyiv.univerpulse.myconnectionpool.service.RegistrationService;

public class RegistrationServiceImpl implements RegistrationService {

    @Override
    public void saveUser(UserDTO userDTO) {

        try {
            TransactionHelper.getInstance().beginTransaction();
            User user = new User.Builder()
                    .setLogin(userDTO)
                    .setName(userDTO)
                    .setPassword(userDTO)
                    .build();
            DaoFactory.getInstance().getUserDao().saveUser(user);
            TransactionHelper.getInstance().commit();
        }catch (DaoException ex){
            TransactionHelper.getInstance().rollback();
            throw new ServiceException(ex);
        }catch (UserAlreadyExistException ex){
            TransactionHelper.getInstance().rollback();
            throw new UserExistException(ex);
        }
    }

}
