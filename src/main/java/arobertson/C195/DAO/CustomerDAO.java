package arobertson.C195.DAO;

import arobertson.C195.Utilities.Database;
import arobertson.C195.Models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Customer Database Access Object. Functions in this class make up different ways of returning user data.
 */
public class CustomerDAO {
    /**
     * Returns a list of all customer.
     * @return ObservableList Customer
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static ObservableList<Customer> getCustomerList() throws SQLException {
        ObservableList<Customer> list = FXCollections.observableArrayList();

        String query = "SELECT Customer_Id, Customer_Name, Address, Postal_Code, Phone, Division_Id FROM client_schedule.customers";
        PreparedStatement ps = Database.connection.prepareStatement(query);
        ResultSet results = ps.executeQuery();

        while (results.next()) {
            int customerId = results.getInt("Customer_Id");
            String name = results.getString("Customer_Name");
            String address = results.getString("Address");
            String postalCode = results.getString("Postal_Code");
            String cPhoneNumber = results.getString("Phone");
            int divisionId = results.getInt("Division_Id");

            Customer customer = new Customer(customerId, name, address, postalCode, cPhoneNumber, divisionId);
            list.add(customer);
        }
        return list;
    }

    /**
     * Inserts customer information into the database.
     * @param newCustomer Customer object.
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static void insertCustomer(Customer newCustomer) throws SQLException {
        String query = "INSERT INTO customers (Customer_Id, Customer_Name, Address, Postal_Code, Phone, Create_Date, " + "Created_By, Last_Update, Last_Updated_By, Division_Id) " + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = Database.connection.prepareStatement(query);

        ps.setInt(1, newCustomer.getCustomerId());
        ps.setString(2, newCustomer.getCustomerName());
        ps.setString(3, newCustomer.getAddress());
        ps.setString(4, newCustomer.getPostalCode());
        ps.setString(5, newCustomer.getPhoneNumber());
        ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(7, newCustomer.getCreatedBy());
        ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(9, newCustomer.getLastUpdatedBy());
        ps.setInt(10, newCustomer.getDivisionId());

        ps.executeUpdate();
    }

    /**
     * Updates a customer in the database.
     * @param customer Customer object
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static void updateCustomer(Customer customer) throws SQLException {
        String query = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, " + "Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? " + "WHERE Customer_ID = ?";

        PreparedStatement ps = Database.connection.prepareStatement(query);

        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhoneNumber());
        ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(6, customer.getLastUpdatedBy());
        ps.setInt(7, customer.getDivisionId());
        ps.setInt(8, customer.getCustomerId());

        ps.executeUpdate();
    }

    /**
     * Deletes a customer from the database by the customerId provided.
     * @param customerId The ID of the customer that needs to be deleted.
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static void deleteCustomer(int customerId) throws SQLException {
        String query = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = Database.connection.prepareStatement(query);
        ps.setInt(1, customerId);
        ps.executeUpdate();
    }

    /**
     * Returns true or false if appointment list is empty for a customer or not.
     * @param customerId customer id for the search for appointments.
     * @return True or False
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static boolean appointmentListIsEmpty(int customerId) throws SQLException {
        String query = "SELECT COUNT(*) as count FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = Database.connection.prepareStatement(query);
        ps.setInt(1, customerId);
        ResultSet results = ps.executeQuery();

        if (results.next()) {
            return results.getInt("count") == 0;
        }
        return true;
    }
}
