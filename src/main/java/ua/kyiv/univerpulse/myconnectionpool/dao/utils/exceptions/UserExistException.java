package ua.kyiv.univerpulse.myconnectionpool.dao.utils.exceptions;

public class UserExistException extends RuntimeException {

    public UserExistException(Exception ex){
        super(ex);
    }

}
