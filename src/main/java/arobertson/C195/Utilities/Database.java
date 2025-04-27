package arobertson.C195.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database class that establishes database connection
 */
public abstract class Database {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String userName = "sqlUser";
    private static final String password = "Passw0rd!";
    public static Connection connection;
    public static String connectionStatus;

    /**
     * openConnection() method opens connection and prints successful message and prints error if there is one
     */
    public static void openConnection() throws SQLException{
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // reference Connection object
            connectionStatus = "Database Connection successful!";
            System.out.println(connectionStatus);
        }
        catch(ClassNotFoundException e) {
            System.out.println("Error Class Not Found:" + e.getMessage());
        }
    }

    /**
     * closeConnection() method closes connection
     */
    public static void closeConnection() {
        try {
            connection.close();
            connectionStatus = "Database Connection closed!";
            System.out.println(connectionStatus);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
