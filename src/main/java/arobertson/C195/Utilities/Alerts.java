package arobertson.C195.Utilities;

import arobertson.C195.DAO.AppointmentDAO;
import arobertson.C195.MainApplication;
import arobertson.C195.Models.Appointment;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utility class for displaying various types of alerts to the user.
 */
public class Alerts {
    /**
     * Resource bundle for managing localized language strings.
     */
    static ResourceBundle lang = ResourceBundle.getBundle("language", Locale.getDefault());

    /**
     * Displays a confirmation alert with customizable messages based on the provided ID.
     *
     * @param confirmationId An integer identifying the type of confirmation needed.
     * 1: Cancel confirmation.
     * 2: Deletion confirmation.
     * @return {@code true} if the user clicks the OK button, {@code false} otherwise.
     */
    public static boolean alertConfirm(int confirmationId) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        switch (confirmationId) {
            case 1:
                alert.setTitle(lang.getString("cancelConfirmation"));
                alert.setContentText(lang.getString("confirmCancel"));
                return alert.showAndWait().get() == ButtonType.OK;
            case 2:
                alert.setTitle(lang.getString("deleteConfirmation"));
                alert.setContentText(lang.getString("confirmDelete"));
                return alert.showAndWait().get() == ButtonType.OK;
        }
        return false;
    }

    /**
     * Displays a warning alert with customizable messages based on the provided ID.
     *
     * @param warningId An integer identifying the type of warning to display.
     * 1: Selection warning for updating an appointment.
     * 2: Selection warning for updating a customer.
     * 3: Selection warning for deleting an appointment.
     * 4: Selection warning for deleting a customer.
     */
    public static void alertWarning(int warningId){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        switch (warningId){
            case 1:
                alert.setTitle(lang.getString("selectionWarning"));
                alert.setContentText(lang.getString("selectAppointmentUpdate"));
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle(lang.getString("selectionWarning"));
                alert.setContentText(lang.getString("selectCustomerUpdate"));
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle(lang.getString("selectionWarning"));
                alert.setContentText(lang.getString("selectAppointmentDelete"));
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle(lang.getString("selectionWarning"));
                alert.setContentText(lang.getString("selectCustomerDelete"));
                alert.showAndWait();
                break;
        }
    }

    /**
     * Displays an information alert with customizable messages based on the provided ID.
     *
     * @param infoId An integer identifying the type of information to display.
     * 1: Success message for adding an appointment.
     * 2: Success message for updating an appointment.
     * 3: Success message for deleting an appointment.
     * 4: Success message for adding a customer.
     * 5: Success message for updating a customer.
     * 6: Success message for deleting a customer.
     */
    public static void alertInfo(int infoId){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        switch (infoId){
            case 1:
                alert.setTitle(lang.getString("success"));
                alert.setContentText(lang.getString("appointmentAddSuccess"));
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle(lang.getString("success"));
                alert.setContentText(lang.getString("appointmentUpdateSuccess"));
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle(lang.getString("success"));
                alert.setContentText(lang.getString("appointmentDeleteSuccess"));
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle(lang.getString("success"));
                alert.setContentText(lang.getString("customerAddSuccess"));
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle(lang.getString("success"));
                alert.setContentText(lang.getString("customerUpdateSuccess"));
                alert.showAndWait();
                break;
            case 6:
                alert.setTitle(lang.getString("success"));
                alert.setContentText(lang.getString("customerDeleteSuccess"));
                alert.showAndWait();
                break;
        }
    }

    /**
     * Displays an error alert with customizable messages based on the provided ID.
     * The error messages for credential issues are localized based on the current locale.
     *
     * @param errorId An integer identifying the type of error to display.
     * 1: Error for a blank username.
     * 2: Error for a blank password.
     * 3: Error for invalid credentials.
     * 4: Error indicating failure to update tables.
     * 5: Error indicating failure to add data to a table.
     * 6: Error indicating failure to update data in a table.
     * 7: Error indicating failure to delete data in a table.
     * 8: Error indicating a conflicting customer appointment schedule.
     * 9: Error indicating failure to add an appointment.
     * 10: Error indicating failure to load appointment data.
     * 11: Error indicating failure to load customer data.
     * 12: Generic error indicating failure to delete a record.
     * 13: Error indicating that customers with existing appointments cannot be deleted.
     * 14: Error indicating failure to add a customer.
     */
    public static void alertError(int errorId){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (errorId){
            case 1:
                alert.setTitle(lang.getString("CredentialsError"));
                alert.setContentText(lang.getString("BlankUser"));
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle(lang.getString("CredentialsError"));
                alert.setContentText(lang.getString("BlankPassword"));
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle(lang.getString("CredentialsError"));
                alert.setContentText(lang.getString("BadCredentials"));
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle(lang.getString("tableError"));
                alert.setContentText(lang.getString("tableErrorContext"));
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle(lang.getString("addingError"));
                alert.setContentText("An error occurred while adding data to table.");
                alert.showAndWait();
                break;
            case 6:
                alert.setTitle(lang.getString("updatingError"));
                alert.setContentText("An error occurred while updating data in the table.");
                alert.showAndWait();
                break;
            case 7:
                alert.setTitle(lang.getString("deletingError"));
                alert.setContentText("An error occurred while deleting data in the table.");
                alert.showAndWait();
                break;
            case 8:
                alert.setTitle("Conflicting Schedule");
                alert.setContentText("The customer has an overlapping appointment scheduled.");
                alert.showAndWait();
                break;
            case 9:
                alert.setTitle(lang.getString("addingError"));
                alert.setContentText("An error occurred while adding the appointment. \nSystem failed to add appointment.");
                alert.showAndWait();
                break;
            case 10:
                alert.setTitle(lang.getString("dataLoadingError"));
                alert.setContentText(lang.getString("failedAppointmentLoading"));
                alert.showAndWait();
                break;
            case 11:
                alert.setTitle(lang.getString("dataLoadingError"));
                alert.setContentText(lang.getString("failedCustomerLoading"));
                alert.showAndWait();
                break;
            case 12:
                alert.setTitle(lang.getString("deletingError"));
                alert.setContentText(lang.getString("failedDeletion"));
                alert.showAndWait();
                break;
            case 13:
                alert.setTitle(lang.getString("deletingError"));
                alert.setContentText(lang.getString("failedExistingAppointments"));
                alert.showAndWait();
                break;
            case 14:
                alert.setTitle(lang.getString("addingError"));
                alert.setContentText(lang.getString("failedAddingCustomer"));
                alert.showAndWait();
                break;
        }
    }

    /**
     * Displays an error alert specifically for input validation failures, including an additional message.
     *
     * @param additionalMessage A String containing additional information about the input error.
     */
    public static void alertErrorInputs(String additionalMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(lang.getString("inputError"));
        alert.setContentText(lang.getString("inputErrorContext")+"\n"+additionalMessage);
        alert.showAndWait();
    }

    /**
     * Displays a warning alert to confirm application exit.
     * If the user confirms, the database connection is closed and the stage is closed.
     * The alert message is localized.
     *
     * @param stage The primary stage of the application.
     */
    public static void alertExit(Stage stage){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(lang.getString("exitConfirmation"));
        alert.setContentText(lang.getString("wannaExit"));
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            Database.closeConnection();
            stage.close();
        } else if (alert.getResult() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    /**
     * Displays a warning alert to confirm user logout.
     * If the user confirms, the current stage is closed, the database connection is closed,
     * and a new instance of the main application is started on the same stage.
     *
     * @param stage The current stage of the application.
     * @throws SQLException If an error occurs while closing the database connection.
     * @throws IOException  If an error occurs while starting the new main application.
     */
    public static void alertLogout(Stage stage) throws SQLException, IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(lang.getString("logoutConfirmation"));
        alert.setContentText(lang.getString("logoutConfirmationContext"));
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            stage.close();
            Database.closeConnection();
            new MainApplication().start(stage);
        } else if (alert.getResult() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    /**
     * Checks for any appointments starting within the next 15 minutes and displays an informational alert.
     * If an upcoming appointment is found, the alert will display the appointment ID and start time
     * in the format "MM-dd-yyy HH:mm". If no upcoming appointments are found, a corresponding message is displayed.
     * The alert messages are localized.
     */
    public static void alertUpcomingApps (){
        try {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime soon = now.plusMinutes(15);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(lang.getString("upcomingAppointment"));
            boolean found = false;
            for (Appointment app : AppointmentDAO.getAllAppointments()) {
                LocalDateTime start = app.getStart();
                if (start.isAfter(now) && start.isBefore(soon)) {
                    alert.setContentText(String.format(lang.getString("upcomingApp"),app.getAppointmentId(),start.format(DateTimeFormatter.ofPattern("MM-dd-yyy HH:mm"))));
                    found = true;
                    break;
                }
            }

            if (!found) {
                alert.setContentText(lang.getString("noUpcomingApp"));
            }
            alert.showAndWait();
        } catch (SQLException e) {
            Alerts.alertError(10);
        }
    }
}