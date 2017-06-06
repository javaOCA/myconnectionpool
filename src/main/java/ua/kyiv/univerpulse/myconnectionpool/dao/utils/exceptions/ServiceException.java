package ua.kyiv.univerpulse.myconnectionpool.dao.utils.exceptions;

public class ServiceException extends RuntimeException {

    public ServiceException(Exception e){
        super(e);
    }

}
