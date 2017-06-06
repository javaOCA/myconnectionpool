package ua.kyiv.univerpulse.myconnectionpool.dao.utils;

import java.io.Closeable;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionProxy implements Closeable {

    private MyConnection connection;
    private boolean isTransaction;

    public ConnectionProxy(MyConnection connection) {
        this.connection = connection;
    }

    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    public PreparedStatement createPreparedStatement(String selectUser) throws SQLException {
        return connection.prepareStatement(selectUser);
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if(!isTransaction)
        try {
            connection.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rollback(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setTransaction(boolean transaction) {
        isTransaction = transaction;
    }
}
