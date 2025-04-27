package arobertson.C195.DAO;

import arobertson.C195.Utilities.Database;
import arobertson.C195.Models.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contact Database Access Object. Functions in this class make up different ways of returning contact data.
 */
public class ContactDAO {
    /**
     * Returns a list of all contacts structured by the Contact class.
     * @return ObservableList Contact
     */
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

    /**
     * Returns the contact with the provided contact ID.
     * @param contactId Id of the contact
     * @return Returns the contact.
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
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
