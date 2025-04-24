package arobertson.C195.DAO;

import Utilities.Database;
import arobertson.C195.Models.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDAO {

    public static ObservableList<Contact> getAllContacts() throws SQLException {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        String query = "SELECT Contact_ID, Contact_Name, Email FROM contacts";

        try (PreparedStatement ps = Database.connection.prepareStatement(query)) {
            ResultSet results = ps.executeQuery();

            while (results.next()) {
                Contact contact = new Contact(
                        results.getInt("Contact_ID"),
                        results.getString("Contact_Name"),
                        results.getString("Email")
                );
                contacts.add(contact);
            }
        }
        return contacts;
    }
    public static Contact getContact(int contactId) throws SQLException {
        String query = "SELECT Contact_ID, Contact_Name, Email FROM contacts WHERE Contact_ID = ?";

        try (PreparedStatement ps = Database.connection.prepareStatement(query)) {
            ps.setInt(1, contactId);
            ResultSet results = ps.executeQuery();

            if (results.next()) {
                return new Contact(
                        results.getInt("Contact_ID"),
                        results.getString("Contact_Name"),
                        results.getString("Email")
                );
            }
        }
        return null;
    }
}
