package arobertson.C195.DAO;

import Utilities.Database;
import arobertson.C195.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User Database object. The functions in this class are specifically for User Object.
 */
public class UserDAO {
    /**
     * Returns true or false if the credentials provided match the database to log the user in.
     * @param username Username from login form
     * @param password Password from login form
     * @return True or False
     */
    public static boolean userLogin(String username, String password){
        try{
            PreparedStatement checkLogin = Database.connection.prepareStatement("SELECT * FROM users WHERE User_Name = ? AND Password = ?");
            checkLogin.setString(1,username);
            checkLogin.setString(2,password);
            ResultSet results = checkLogin.executeQuery();
            if(results.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Returns a list of all users.
     * @return ObservableList User
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> users = FXCollections.observableArrayList();
        String sql = "SELECT User_ID, User_Name FROM users";

        try (PreparedStatement ps = Database.connection.prepareStatement(sql)) {
            ResultSet results = ps.executeQuery();

            while (results.next()) {
                User user = new User(
                        results.getInt("User_ID"),
                        results.getString("User_Name")
                );
                users.add(user);
            }
        }
        return users;
    }

    /**
     * Returns a new user object from the database using the user id.
     * @param id ID of the user
     * @return User Object
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static User getUserById(int id) throws SQLException {
        PreparedStatement sqlQuery = Database.connection.prepareStatement("SELECT * FROM users WHERE User_ID = '" + id + "'");
        ResultSet results = sqlQuery.executeQuery();
        results.next();

        int UserId = results.getInt("User_Id");
        String Username = results.getString("User_Name");

        return new User(UserId,Username);
    }

}
