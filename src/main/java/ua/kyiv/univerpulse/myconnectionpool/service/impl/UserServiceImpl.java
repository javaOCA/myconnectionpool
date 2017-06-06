package ua.kyiv.univerpulse.myconnectionpool.service.impl;

import ua.kyiv.univerpulse.myconnectionpool.dao.DaoFactory;
import ua.kyiv.univerpulse.myconnectionpool.dao.utils.exceptions.DaoException;
import ua.kyiv.univerpulse.myconnectionpool.domains.User;
import ua.kyiv.univerpulse.myconnectionpool.dto.UserDTO;
import ua.kyiv.univerpulse.myconnectionpool.service.UserService;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> usersDTO = null;
        try {
            List<User> users = DaoFactory.getInstance().getUserDao().getAllUser();
            usersDTO = users.stream()
                    .map(user -> new UserDTO.Builder()
                            .setLogin(user)
                            .setName(user)
                            .setPassword(user).build())
                    .collect(Collectors.toList());
        } catch (DaoException ex) {
            return usersDTO;
        }
        return usersDTO;
    }
}
