package ua.kyiv.univerpulse.myconnectionpool.dao.utils.exceptions;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(Exception e){
        super(e);
    }

}
