package ua.kyiv.univerpulse.myconnectionpool.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class MyConnectionPool {

    private static MyConnectionPool pool = new MyConnectionPool();
    private Queue<Connection> queue = new LinkedList<Connection>();
    private String serverName;
    private String databaseName;
    private String user;
    private String password;
    private int maxConnections;

    private MyConnectionPool() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:postgresql://"+serverName+"/" + databaseName;
        for (int i=0; i<maxConnections; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, user, password);
                queue.add(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public synchronized MyConnection getConnection() throws SQLException {
            Connection connection = queue.poll();
            if(connection == null){
                connection = createConnection();
            }
            return new MyConnection(queue, connection);
    }

    private Connection createConnection() throws SQLException {
        String url = "jdbc:postgresql://"+serverName+"/" + databaseName;
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public static MyConnectionPool getInstance(){
        return pool;
    }

    public void close(){
        Connection connection = queue.poll();
        while (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection = queue.poll();
        }
    }

}
