package ua.kyiv.univerpulse.myconnectionpool.dao.utils;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Queue;

public class MyConnection implements Closeable {

    private Queue<Connection> queue;
    private Connection connection;

    public MyConnection(Queue<Connection> queue, Connection connection) {
        this.queue = queue;
        this.connection = connection;
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    @Override
    public void close() throws IOException {
        queue.add(connection);
    }

    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    public PreparedStatement prepareStatement(String selectUser) throws SQLException {
        return connection.prepareStatement(selectUser);
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }
}
