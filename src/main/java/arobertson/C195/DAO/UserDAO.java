package arobertson.C195.DAO;

import Utilities.Database;
import arobertson.C195.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class UserDAO {
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

    public static User getUserByUsername(String username) throws SQLException {
        PreparedStatement sqlQuery = Database.connection.prepareStatement("SELECT * FROM users WHERE User_Name = '" + username+ "'");
        ResultSet results = sqlQuery.executeQuery();
        results.next();

        int userId = results.getInt("User_Id");
        String password = results.getString("Password");
        LocalDateTime createdDate = results.getTimestamp("Create_Date").toLocalDateTime();
        String createdBy = results.getString("Created_By");
        Timestamp lastUpdated = results.getTimestamp("Last_Update");
        String lastUpdatedBy = results.getString("Last_Updated_By");

        return new User(userId,username,password,createdDate,createdBy,lastUpdated,lastUpdatedBy);
    }

    public static User getUserById(int id) throws SQLException {
        PreparedStatement sqlQuery = Database.connection.prepareStatement("SELECT * FROM users WHERE User_ID = '" + id + "'");
        ResultSet results = sqlQuery.executeQuery();
        results.next();

        int UserId = results.getInt("User_Id");
        String Username = results.getString("User_Name");

        return new User(UserId,Username);
    }

}
