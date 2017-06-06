package ua.kyiv.univerpulse.myconnectionpool.dao.utils;


import ua.kyiv.univerpulse.myconnectionpool.dao.utils.exceptions.TransactionException;

import java.sql.SQLException;

public class TransactionHelper {

    private static TransactionHelper instance = new TransactionHelper();
//    private Jdbc3PoolingDataSource source = new Jdbc3PoolingDataSource();
    private MyConnectionPool source = MyConnectionPool.getInstance();
    private ThreadLocal<ConnectionProxy> threadLocal = new ThreadLocal<>();

    private TransactionHelper(){
        source.setServerName("127.0.0.1");
        source.setDatabaseName("servletdb");
        source.setUser("postgres");
        source.setPassword("968trp");
        source.setMaxConnections(10);
    }


    public static TransactionHelper getInstance() {
        return instance;
    }

    public ConnectionProxy getConnection() {
        ConnectionProxy connectionProxy = threadLocal.get();
        if(connectionProxy == null){
            try {
               connectionProxy = new ConnectionProxy(source.getConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connectionProxy;
    }

    public void commit() {
        ConnectionProxy connectionProxy = threadLocal.get();
        if(connectionProxy==null){
            throw new TransactionException();
        }
        connectionProxy.commit();
        threadLocal.remove();
        connectionProxy.setTransaction(false);
        connectionProxy.close();
    }

    public void beginTransaction() {
        MyConnection connection;
        try {
            connection = source.getConnection();
            connection.setAutoCommit(false);
            ConnectionProxy connectionProxy = new ConnectionProxy(connection);
            connectionProxy.setTransaction(true);
            threadLocal.set(connectionProxy);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback() {
        ConnectionProxy connectionProxy = threadLocal.get();
        if(connectionProxy == null){
            throw new TransactionException();
        }
        connectionProxy.rollback();
        threadLocal.remove();
        connectionProxy.setTransaction(false);
        connectionProxy.close();
    }
}
