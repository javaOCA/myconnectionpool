package ua.kyiv.univerpulse.myconnectionpool.service;

import ua.kyiv.univerpulse.myconnectionpool.dto.UserDTO;
import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

}
