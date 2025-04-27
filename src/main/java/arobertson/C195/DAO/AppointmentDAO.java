package arobertson.C195.DAO;

import arobertson.C195.Utilities.Database;
import arobertson.C195.Models.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Appointment Database Access Object. Functions in this class make up different ways of returning appointment data.
 */
public class AppointmentDAO {
    /**
     * Returns a list of Appointments for this week and structured by the appointment class.
     * @return ObservableList Appointment
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static ObservableList<Appointment> getAppointmentsThisWeek() throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE YEARWEEK(Start, 1) = YEARWEEK(CURDATE(), 1)";

        try (PreparedStatement ps = Database.connection.prepareStatement(sql)) {
            ResultSet results = ps.executeQuery();

            while (results.next()) {
                Appointment appointment = new Appointment(
                        results.getInt("Appointment_ID"),
                        results.getInt("Customer_ID"),
                        results.getInt("User_ID"),
                        results.getInt("Contact_ID"),
                        results.getString("Title"),
                        results.getString("Description"),
                        results.getString("Location"),
                        results.getString("Type"),
                        results.getTimestamp("Start").toLocalDateTime(),
                        results.getTimestamp("End").toLocalDateTime()
                );
                appointmentList.add(appointment);
            }
        }
        return appointmentList;
    }

    /**
     * Returns a list of Appointments for this month and structured by the appointment class.
     * @return ObservableList Appointment
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static ObservableList<Appointment> getAppointmentsThisMonth() throws SQLException {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE MONTH(Start) = MONTH(CURDATE()) AND YEAR(Start) = YEAR(CURDATE())";

        try (PreparedStatement ps = Database.connection.prepareStatement(sql)) {
            ResultSet results = ps.executeQuery();

            while (results.next()) {
                Appointment appointment = new Appointment(
                        results.getInt("Appointment_ID"),
                        results.getInt("Customer_ID"),
                        results.getInt("User_ID"),
                        results.getInt("Contact_ID"),
                        results.getString("Title"),
                        results.getString("Description"),
                        results.getString("Location"),
                        results.getString("Type"),
                        results.getTimestamp("Start").toLocalDateTime(),
                        results.getTimestamp("End").toLocalDateTime()
                );
                appointmentList.add(appointment);
            }
        }
        return appointmentList;
    }

    /**
     * Returns a list of all Appointments and structured by the appointment class.
     * @return ObservableList Appointment
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException{
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        String query = "SELECT * FROM appointments";

        try (PreparedStatement ps = Database.connection.prepareStatement(query)) {
            ResultSet results = ps.executeQuery();

            while (results.next()) {
                Appointment appointment = new Appointment(
                        results.getInt("Appointment_ID"),
                        results.getInt("Customer_ID"),
                        results.getInt("User_ID"),
                        results.getInt("Contact_ID"),
                        results.getString("Title"),
                        results.getString("Description"),
                        results.getString("Location"),
                        results.getString("Type"),
                        results.getTimestamp("Start").toLocalDateTime(),
                        results.getTimestamp("End").toLocalDateTime()
                );
                appointmentList.add(appointment);
            }
        }
        return appointmentList;
    }

    /**
     * Returns a list of Appointments for a specific contact and structured by the appointment class.
     * @param contactId - ID of the contact the list of appointments will be for.
     * @return Returns a list of appointments for the provided contact ID.
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static ObservableList<Appointment> getAppointmentsByContactId(int contactId) throws SQLException{
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        String query = "SELECT * FROM appointments WHERE Contact_ID = ?";

        try (PreparedStatement ps = Database.connection.prepareStatement(query)) {
            ps.setInt(1, contactId);
            ResultSet results = ps.executeQuery();

            while (results.next()) {
                Appointment appointment = new Appointment(
                        results.getInt("Appointment_ID"),
                        results.getInt("Customer_ID"),
                        results.getInt("User_ID"),
                        results.getInt("Contact_ID"),
                        results.getString("Title"),
                        results.getString("Description"),
                        results.getString("Location"),
                        results.getString("Type"),
                        results.getTimestamp("Start").toLocalDateTime(),
                        results.getTimestamp("End").toLocalDateTime()
                );
                appointmentList.add(appointment);
            }
        }
        return appointmentList;
    }

    /**
     *  Returns a list of appointments for a particular country. Structured by the Appointment class.
     * @param countryId - Id for the country that the list of appointments will be returned.
     * @return - List of appointments based on countryId
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static ObservableList<Appointment> getAppointmentsByCountryId(int countryId) throws SQLException{
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        String query = "SELECT ap.Appointment_ID, ap.Title, ap.Type, ap.Description, ap.Start, ap.End, ap.Customer_ID FROM appointments AS ap JOIN customers AS cu ON ap.Customer_ID = cu.Customer_ID JOIN first_level_divisions AS fld ON cu.Division_ID = fld.Division_ID JOIN countries AS cy ON fld.Country_ID = cy.Country_ID WHERE cy.Country_ID = ?";

        try (PreparedStatement ps = Database.connection.prepareStatement(query)) {
            ps.setInt(1, countryId);
            ResultSet results = ps.executeQuery();

            while (results.next()) {
                Appointment appointment = new Appointment(
                        results.getInt("Appointment_ID"),
                        results.getString("Title"),
                        results.getString("Type"),
                        results.getString("Description"),
                        results.getTimestamp("Start").toLocalDateTime(),
                        results.getTimestamp("End").toLocalDateTime(),
                        results.getInt("Customer_ID")
                );
                appointmentList.add(appointment);
            }
        }
        return appointmentList;
    }

//

    /**
     * Void function inserts appointment data into the database.
     * @param appointment Appointment object.
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static void insert(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, " +
                "Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = Database.connection.prepareStatement(sql)) {
            ps.setInt(1, appointment.getAppointmentId());
            ps.setString(2, appointment.getTitle());
            ps.setString(3, appointment.getDescription());
            ps.setString(4, appointment.getLocation());
            ps.setString(5, appointment.getType());
            ps.setTimestamp(6, Timestamp.valueOf(appointment.getStart()));
            ps.setTimestamp(7, Timestamp.valueOf(appointment.getEnd()));
            ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(9, "admin"); // Replace with actual user from UserDAO.getCurrentUser().getUsername()
            ps.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(11, "admin"); // Replace with actual user from UserDAO.getCurrentUser().getUsername()
            ps.setInt(12, appointment.getCustomerId());
            ps.setInt(13, appointment.getUserId());
            ps.setInt(14, appointment.getContactId());

            int rowsAffected = ps.executeUpdate();
        }
    }

    /**
     * Deletes appointment by ID.
     * @param appointmentId Id for the appointment being deleted.
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static void delete(int appointmentId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";

        try (PreparedStatement ps = Database.connection.prepareStatement(sql)) {
            ps.setInt(1, appointmentId);

            int rowsAffected = ps.executeUpdate();
        }
    }

    /**
     * Updates appointment with new appointment data.
     * @param appointment Appointment object with new data.
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static boolean update(Appointment appointment) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, " +
                "Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, " +
                "User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";

        try (PreparedStatement ps = Database.connection.prepareStatement(sql)) {
            ps.setString(1, appointment.getTitle());
            ps.setString(2, appointment.getDescription());
            ps.setString(3, appointment.getLocation());
            ps.setString(4, appointment.getType());
            ps.setTimestamp(5, Timestamp.valueOf(appointment.getStart()));
            ps.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(8, "admin"); // Replace with actual user from UserDAO.getCurrentUser().getUsername()
            ps.setInt(9, appointment.getCustomerId());
            ps.setInt(10, appointment.getUserId());
            ps.setInt(11, appointment.getContactId());
            ps.setInt(12, appointment.getAppointmentId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }

    /**
     * Returns a list of appointments total and type for a provided month.
     * @param month Integer value for month.
     * @return ObservableList Appointment
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static ObservableList<Appointment> getCustomerAppTotals (int month) throws SQLException{
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        String query = "SELECT Type, COUNT(Appointment_ID) AS count FROM appointments WHERE Month(Start) = ? GROUP BY Type";
        try (PreparedStatement ps = Database.connection.prepareStatement(query)) {
            ps.setInt(1, month);
            ResultSet results = ps.executeQuery();

            while (results.next()) {
                appointments.add(new Appointment(
                        results.getString("type"),
                        results.getInt("count")
                ));
            }
        }
        return appointments;
    }
}
