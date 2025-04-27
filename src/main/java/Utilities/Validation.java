package Utilities;

import arobertson.C195.Models.Appointment;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utility class for performing various input validation checks.
 */
public class Validation {
    static ResourceBundle lang = ResourceBundle.getBundle("language", Locale.getDefault());
    /**
     * Validates the inputs for adding or updating an appointment.
     * Checks if all required fields (title, description, location, contact, type,
     * start date, start time, end date, end time, customer, user) are filled.
     * If any field is empty or not selected, an error alert is displayed.
     *
     * @param title       The TextField for the appointment title.
     * @param description The TextField for the appointment description.
     * @param location    The TextField for the appointment location.
     * @param contact     The ComboBox for selecting the contact.
     * @param type        The TextField for the appointment type.
     * @param startDate   The DatePicker for the appointment start date.
     * @param startTime   The ComboBox for selecting the appointment start time.
     * @param endDate     The DatePicker for the appointment end date.
     * @param endTime     The ComboBox for selecting the appointment end time.
     * @param customer    The ComboBox for selecting the customer.
     * @param user        The ComboBox for selecting the user.
     * @return {@code true} if all input fields are valid, {@code false} otherwise.
     */
    @FXML
    public static boolean validateAppointmentInputes(TextField title, TextField description, TextField location, ComboBox<String> contact, TextField type, DatePicker startDate, ComboBox<LocalTime> startTime, DatePicker endDate, ComboBox<LocalTime> endTime, ComboBox<String> customer, ComboBox<String> user){
        StringBuilder errorMessage = new StringBuilder();
        if (title.getText().isEmpty()) {
            errorMessage.append("inputError7").append("\n");
        }
        if (description.getText().isEmpty()) {
            errorMessage.append(lang.getString("inputError8")).append("\n");
        }
        if (location.getText().isEmpty()) {
            errorMessage.append(lang.getString("inputError9")).append("\n");
        }
        if (contact.getValue() == null) {
            errorMessage.append(lang.getString("inputError10")).append("\n");
        }
        if (type.getText().isEmpty()) {
            errorMessage.append(lang.getString("inputError11")).append("\n");
        }
        if (startDate.getValue() == null) {
            errorMessage.append(lang.getString("inputError12")).append("\n");
        }
        if (startTime.getValue() == null) {
            errorMessage.append(lang.getString("inputError13")).append("\n");
        }
        if (endDate.getValue() == null) {
            errorMessage.append(lang.getString("inputError14")).append("\n");
        }
        if (endTime.getValue() == null) {
            errorMessage.append(lang.getString("inputError15")).append("\n");
        }
        if (customer.getValue() == null) {
            errorMessage.append(lang.getString("inputError16")).append("\n");
        }
        if (user.getValue() == null) {
            errorMessage.append(lang.getString("inputError17")).append("\n");
        }

        if (!errorMessage.isEmpty()) {
            Alerts.alertErrorInputs(errorMessage.toString());
            return false;
        }

        return true;
    }

    /**
     * Validates if the proposed appointment time overlaps with any existing appointments for the same customer.
     * It also performs a basic check to ensure the start time is not after or equal to the end time.
     *
     * @param appointments        An ObservableList of existing appointments.
     * @param customerId          The ID of the customer for whom the appointment is being scheduled.
     * @param start               The LocalDateTime representing the start time of the proposed appointment.
     * @param end                 The LocalDateTime representing the end time of the proposed appointment.
     * @param excludeAppointmentId Optional: The ID of an appointment to exclude from the overlap check
     * (used when updating an existing appointment). Pass {@code null} when adding a new appointment.
     * @return {@code true} if the appointment time is valid and does not overlap with existing appointments, {@code false} otherwise.
     */
    public static boolean validateAppointmentTime(ObservableList<Appointment> appointments, int customerId, LocalDateTime start, LocalDateTime end, Integer excludeAppointmentId) {

        // Validate basic time logic
        if (start.isAfter(end) || start.isEqual(end)) {
            return false;
        }

        // Check for overlaps with existing appointments
        for (Appointment existing : appointments) {
            // Skip if this is the appointment being updated
            if (excludeAppointmentId != null && existing.getAppointmentId() == excludeAppointmentId) {
                continue;
            }

            // Only check appointments for the same customer
            if (existing.getCustomerId() == customerId) {
                LocalDateTime existingStart = existing.getStart();
                LocalDateTime existingEnd = existing.getEnd();

                // Check for overlaps
                boolean overlaps = !(end.isEqual(existingStart) || end.isBefore(existingStart) ||
                        start.isEqual(existingEnd) || start.isAfter(existingEnd));

                if (overlaps) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Overloaded method for {@link #validateAppointmentTime(ObservableList, int, LocalDateTime, LocalDateTime, Integer)}
     * that does not exclude any appointment during the overlap check. This is typically used when adding a new appointment.
     *
     * @param appointments An ObservableList of existing appointments.
     * @param customerId   The ID of the customer for whom the appointment is being scheduled.
     * @param start        The LocalDateTime representing the start time of the proposed appointment.
     * @param end          The LocalDateTime representing the end time of the proposed appointment.
     * @return {@code true} if the appointment time is valid and does not overlap with existing appointments, {@code false} otherwise.
     */
    public static boolean validateAppointmentTime(ObservableList<Appointment> appointments, int customerId, LocalDateTime start, LocalDateTime end) {
        return validateAppointmentTime(appointments, customerId, start, end, null);
    }

    /**
     * Validates the inputs for adding or updating a customer.
     * Checks if all required fields (name, address, postal code, phone number, country, state/division) are filled.
     * If any field is empty or not selected, an error alert is displayed.
     *
     * @param nameInput       The TextField for the customer's name.
     * @param addressInput    The TextField for the customer's address.
     * @param postalCodeInput The TextField for the customer's postal code.
     * @param phoneNumberInput The TextField for the customer's phone number.
     * @param countryInput    The ComboBox for selecting the customer's country.
     * @param stateInput      The ComboBox for selecting the customer's state or division.
     * @return {@code true} if all input fields are valid, {@code false} otherwise.
     */
    public static boolean validateCustomerInputs(TextField nameInput, TextField addressInput,TextField postalCodeInput,TextField phoneNumberInput,ComboBox<String> countryInput,ComboBox<String> stateInput) {
        StringBuilder errorMessage = new StringBuilder();

        if (nameInput.getText().isEmpty()) {
            errorMessage.append(lang.getString("inputError1")).append("\n");
        }
        if (addressInput.getText().isEmpty()) {
            errorMessage.append(lang.getString("inputError2")).append("\n");
        }
        if (postalCodeInput.getText().isEmpty()) {
            errorMessage.append(lang.getString("inputError3")).append("\n");
        }
        if (phoneNumberInput.getText().isEmpty()) {
            errorMessage.append(lang.getString("inputError4")).append("\n");
        }
        if (countryInput.getValue() == null) {
            errorMessage.append(lang.getString("inputError5")).append("\n");
        }
        if (stateInput.getValue() == null) {
            errorMessage.append(lang.getString("inputError6")).append("\n");
        }

        if (!errorMessage.isEmpty()) {
            Alerts.alertErrorInputs(errorMessage.toString());
            return false;
        }

        return true;
    }
}