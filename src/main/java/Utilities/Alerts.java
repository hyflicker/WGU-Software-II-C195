package Utilities;

import arobertson.C195.MainApplication;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Alerts {
    static ResourceBundle lang = ResourceBundle.getBundle("language");
    public static boolean alertConfirm(int confirmationId) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        switch (confirmationId) {
            case 1:
                alert.setTitle("Cancel Confirmation");
                alert.setContentText("Are you sure you want to cancel?");
                return alert.showAndWait().get() == ButtonType.OK;
            case 2:
                alert.setTitle("Deletion Confirmation");
                alert.setContentText("Are you sure you want to delete this record?");
                return alert.showAndWait().get() == ButtonType.OK;
        }
        return false;
    }

    public static void alertWarning(int warningId){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        switch (warningId){
            case 1:
                alert.setTitle("Selection Warning");
                alert.setContentText("Please select an appointment to update.");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Selection Warning");
                alert.setContentText("Please select a customer to update.");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Selection Warning");
                alert.setContentText("Please select an appointment to delete.");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Selection Warning");
                alert.setContentText("Please select a customer to delete.");
                alert.showAndWait();
                break;


        }
    }
    public static void alertInfo(int infoId){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        switch (infoId){
            case 1:
                alert.setTitle("Success");
                alert.setContentText("Appointment has been added and saved successfully.");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Success");
                alert.setContentText("Appointment has been updated and saved successfully.");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Success");
                alert.setContentText("Appointment has been deleted successfully.");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Success");
                alert.setContentText("Customer has been added and saved successfully.");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Success");
                alert.setContentText("Customer has been updated and saved successfully.");
                alert.showAndWait();
                break;
            case 6:
                alert.setTitle("Success");
                alert.setContentText("Customer has been deleted successfully.");
                alert.showAndWait();
                break;
        }

    }
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
                alert.setTitle("Table Error");
                alert.setContentText("Could not update tables.");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Adding Error");
                alert.setContentText("An error occurred while adding data to table.");
                alert.showAndWait();
                break;
            case 6:
                alert.setTitle("Updating Error");
                alert.setContentText("An error occurred while updating data in the table.");
                alert.showAndWait();
                break;
            case 7:
                alert.setTitle("Deleting Error");
                alert.setContentText("An error occurred while deleting data in the table.");
                alert.showAndWait();
                break;
            case 8:
                alert.setTitle("Conflicting Schedule");
                alert.setContentText("The customer has an overlapping appointment scheduled.");
                alert.showAndWait();
                break;
            case 9:
                alert.setTitle("Error Adding Appointment");
                alert.setContentText("An error occurred while adding the appointment. \nSystem failed to add appointment.");
                alert.showAndWait();
                break;
            case 10:
                alert.setTitle("Data Loading Error");
                alert.setContentText("Failed to load appointment data.");
                alert.showAndWait();
                break;
            case 11:
                alert.setTitle("Data Loading Error");
                alert.setContentText("Failed to load customer data.");
                alert.showAndWait();
                break;
            case 12:
                alert.setTitle("Deletion Error");
                alert.setContentText("Failed to delete record.");
                alert.showAndWait();
                break;
            case 13:
                alert.setTitle("Deletion Error");
                alert.setContentText("Cannot delete customers with existing appointments.");
                alert.showAndWait();
                break;
            case 14:
                alert.setTitle("Error Adding Customer");
                alert.setContentText("An error occurred while adding the customer. \nSystem failed to add customer.");
                alert.showAndWait();
                break;
        }
    }

    public static void alertErrorInputes(String addionalMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setContentText("An error occurred as you are missing required fields.\n"+addionalMessage);
        alert.showAndWait();
    }

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

    public static void alertLogout(Stage stage) throws SQLException, IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Logout Confirmation");
        alert.setContentText("Are you sure you want to logout?");
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
}
