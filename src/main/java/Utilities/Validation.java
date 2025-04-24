package Utilities;

import arobertson.C195.Models.Appointment;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Validation {
    @FXML
    public static boolean validateAppointmentInputes(TextField title, TextField description, TextField location, ComboBox<String> contact, TextField type, DatePicker startDate, ComboBox<LocalTime> startTime, DatePicker endDate, ComboBox<LocalTime> endTime, ComboBox<String> customer, ComboBox<String> user){
        StringBuilder errorMessage = new StringBuilder();
        if (title.getText().isEmpty()) {
            errorMessage.append("Please enter a title.\n");
        }
        if (description.getText().isEmpty()) {
            errorMessage.append("Please enter a description.\n");
        }
        if (location.getText().isEmpty()) {
            errorMessage.append("Please enter a location.\n");
        }
        if (contact.getValue() == null) {
            errorMessage.append("Please select a contact.\n");
        }
        if (type.getText().isEmpty()) {
            errorMessage.append("Please enter a type.\n");
        }
        if (startDate.getValue() == null) {
            errorMessage.append("Please select a start date.\n");
        }
        if (startTime.getValue() == null) {
            errorMessage.append("Please select a start time.\n");
        }
        if (endDate.getValue() == null) {
            errorMessage.append("Please select an end date.\n");
        }
        if (endTime.getValue() == null) {
            errorMessage.append("Please select an end time.\n");
        }
        if (customer.getValue() == null) {
            errorMessage.append("Please select a customer.\n");
        }
        if (user.getValue() == null) {
            errorMessage.append("Please select a user.\n");
        }

        if (!errorMessage.isEmpty()) {
            Alerts.alertErrorInputes(errorMessage.toString());
            return false;
        }

        return true;

    }

    public static boolean validateAppointmentTime(ObservableList<Appointment> appointments, int customerId, LocalDateTime proposedStart, LocalDateTime proposedEnd, Integer excludeAppointmentId) {

        // Validate basic time logic
        if (proposedStart.isAfter(proposedEnd) || proposedStart.isEqual(proposedEnd)) {
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
                boolean overlaps = !(proposedEnd.isEqual(existingStart) || proposedEnd.isBefore(existingStart) ||
                        proposedStart.isEqual(existingEnd) || proposedStart.isAfter(existingEnd));

                if (overlaps) {
                    return false;
                }
            }
        }

        return true;
    }
    
    public static boolean validateAppointmentTime(ObservableList<Appointment> appointments,
                                                 int customerId,
                                                 LocalDateTime proposedStart,
                                                 LocalDateTime proposedEnd) {
        return validateAppointmentTime(appointments, customerId, proposedStart, proposedEnd, null);
    }

    public static boolean validateCustomerInputs(TextField nameInput, TextField addressInput,TextField postalCodeInput,TextField phoneNumberInput,ComboBox<String> countryInput,ComboBox<String> stateInput) {
        StringBuilder errorMessage = new StringBuilder();

        if (nameInput.getText().isEmpty()) {
            errorMessage.append("Please enter customer's name.\n");
        }
        if (addressInput.getText().isEmpty()) {
            errorMessage.append("Please enter customer's address.\n");
        }
        if (postalCodeInput.getText().isEmpty()) {
            errorMessage.append("Please enter customer's postal code.\n");
        }
        if (phoneNumberInput.getText().isEmpty()) {
            errorMessage.append("Please enter customer's phone number.\n");
        }
        if (countryInput.getValue() == null) {
            errorMessage.append("Please enter customer's country.\n");
        }
        if (stateInput.getValue() == null) {
            errorMessage.append("Please enter customer's state or division.\n");
        }

        if (errorMessage.length() > 0) {
            Alerts.alertErrorInputes(errorMessage.toString());
            return false;
        }

        return true;
    }
}
