package ua.kyiv.univerpulse.myconnectionpool.dao;

public class DaoFactory {

    private static DaoFactory instance = new DaoFactory();
    private UserDao userDao = new UserDao();

    private DaoFactory(){}

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
