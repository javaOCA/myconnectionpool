package ua.kyiv.univerpulse.myconnectionpool.dao;

import ua.kyiv.univerpulse.myconnectionpool.dao.utils.ConnectionProxy;
import ua.kyiv.univerpulse.myconnectionpool.dao.utils.TransactionHelper;
import ua.kyiv.univerpulse.myconnectionpool.dao.utils.exceptions.DaoException;
import ua.kyiv.univerpulse.myconnectionpool.dao.utils.exceptions.UserAlreadyExistException;
import ua.kyiv.univerpulse.myconnectionpool.domains.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final static String insertUser = "insert into users (name, login, password) values(?,?,?)";
    private final static String selectUser = "select * from users where login=?";
    private final static String selectAllUser = "select * from users";

    public UserDao(){}

        public void saveUser(User user) {
            try (ConnectionProxy connection =  TransactionHelper.getInstance().getConnection()) {
                PreparedStatement statement = connection.createPreparedStatement(insertUser);
                statement.setString(1,user.getName());
                statement.setString(2,user.getLogin());
                statement.setString(3,user.getPassword());
                statement.executeUpdate();
            } catch (SQLException ex) {
                if (ex.getSQLState().equals("23505")){
                    throw new UserAlreadyExistException(ex);
                } else {
                    throw new DaoException(ex);
                }
            }
        }

   public User getUserByLogin(String login){
       User user = null;
       try(ConnectionProxy connection =  TransactionHelper.getInstance().getConnection()) {
           PreparedStatement statement = connection.createPreparedStatement(selectUser);
           statement.setString(1, login);
           ResultSet resultSet = statement.executeQuery();
           if (resultSet.next()) {
               user = new User();
               user.setName(resultSet.getString("name"));
               user.setLogin(resultSet.getString("login"));
               user.setPassword(resultSet.getString("password"));
           }
       } catch (SQLException e) {
           throw new DaoException(e);
       }
       return user;
   }

   public List<User> getAllUser(){
       List<User> users = new ArrayList<>();
       try(ConnectionProxy connection =  TransactionHelper.getInstance().getConnection()){
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(selectAllUser);
           while (resultSet.next()){
               User user = new User();
               user.setName(resultSet.getString("name"));
               user.setLogin(resultSet.getString("login"));
               user.setPassword(resultSet.getString("password"));
               users.add(user);
           }
       }catch (SQLException ex) {
           throw new DaoException(ex);
       }
       return users;
   }

}
