package arobertson.C195.Controllers;

import arobertson.C195.Utilities.Alerts;
import arobertson.C195.Utilities.InputLoader;
import arobertson.C195.Utilities.Validation;
import arobertson.C195.DAO.AppointmentDAO;
import arobertson.C195.DAO.ContactDAO;
import arobertson.C195.DAO.CustomerDAO;
import arobertson.C195.DAO.UserDAO;
import arobertson.C195.Models.Appointment;
import arobertson.C195.Models.Contact;
import arobertson.C195.Models.Customer;
import arobertson.C195.Models.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * Controls the functionality of the "Update Appointment.fxml" report.
 */
public class UpdateAppointmentController implements Initializable {

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<String> contactInput;

    @FXML
    private Label contactLabel;

    @FXML
    private ComboBox<String> customerIdInput;

    @FXML
    private Label customerIdLabel;

    @FXML
    private TextField descriptionInput;

    @FXML
    private Label descriptionLabel;

    @FXML
    private DatePicker endDateInput;

    @FXML
    private Label endDateLabel;

    @FXML
    private Label endTimeLabel;

    @FXML
    private ComboBox<LocalTime> endtTimeInput;

    @FXML
    private TextField idInput;

    @FXML
    private Label idLabel;

    @FXML
    private TextField locationInput;

    @FXML
    private Label locationLabel;

    @FXML
    private Button saveBtn;

    @FXML
    private DatePicker startDateInput;

    @FXML
    private Label startDateLabel;

    @FXML
    private ComboBox<LocalTime> startTimeInput;

    @FXML
    private Label startTimeLabel;

    @FXML
    private TextField titleInput;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField typeInput;

    @FXML
    private Label typeLabel;

    @FXML
    private ComboBox<String> userIdInput;

    @FXML
    private Label userIdLabel;

    /**
     * Initializes and loads ComboBoxes
     */
    private Appointment selectedAppointment;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            new InputLoader().loadContacts(contactInput);
            new InputLoader().loadTimes(startTimeInput,endtTimeInput);
            new InputLoader().loadCustomers(customerIdInput);
            new InputLoader().loadUsers(userIdInput);
            new InputLoader().diableWeekends(startDateInput,endDateInput);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the appointment that is selected and calls the populateFields function to load the data.
     * @param appointment - Selected appointment from the appointment table.
     */
    public void setAppointment(Appointment appointment) {
        this.selectedAppointment = appointment;
        populateFields();
    }

    /**
     * Populates all the input fields in the Update Appointment view with data from the database.
     */
    private void populateFields() {
        try {
            idInput.setText(String.valueOf(selectedAppointment.getAppointmentId()));
            titleInput.setText(selectedAppointment.getTitle());
            descriptionInput.setText(selectedAppointment.getDescription());
            locationInput.setText(selectedAppointment.getLocation());
            typeInput.setText(selectedAppointment.getType());

            Contact contact = ContactDAO.getContact(selectedAppointment.getContactId());
            if (contact != null) {
                contactInput.setValue(contact.getContactId() + " - " + contact.getContactName());
            }

            Customer customer = CustomerDAO.getCustomerList().filtered(filteredCustomer ->
                    filteredCustomer.getCustomerId() == selectedAppointment.getCustomerId()).get(0);
            customerIdInput.setValue(customer.getCustomerId() + " - " + customer.getCustomerName());

            User user = UserDAO.getUserById(selectedAppointment.getUserId());
            userIdInput.setValue(user.getUserId() + " - " + user.getUsername());

            startDateInput.setValue(selectedAppointment.getStart().toLocalDate());
            endDateInput.setValue(selectedAppointment.getEnd().toLocalDate());
            startTimeInput.setValue(selectedAppointment.getStart().toLocalTime());
            endtTimeInput.setValue(selectedAppointment.getEnd().toLocalTime());

        } catch (SQLException e) {
            Alerts.alertError(10);
        }
    }

    /**
     * When the save button is click is grabs all the fields from the form and validates and saves the data to the database.
     */
    @FXML
    void onSave(){
        if(!Validation.validateAppointmentInputes(titleInput,descriptionInput,locationInput,contactInput, typeInput,startDateInput,startTimeInput,endDateInput,endtTimeInput,customerIdInput,userIdInput)){
            return;
        }

        try{
            int contactId = Integer.parseInt(contactInput.getValue().split(" - ")[0].trim());
            int customerId = Integer.parseInt(customerIdInput.getValue().split(" - ")[0].trim());
            int userId = Integer.parseInt(userIdInput.getValue().split(" - ")[0].trim());

            LocalDateTime startDateTime = LocalDateTime.of(startDateInput.getValue(),startTimeInput.getValue());
            LocalDateTime endDateTime = LocalDateTime.of(endDateInput.getValue(),endtTimeInput.getValue());

            ObservableList<Appointment> appointments = AppointmentDAO.getAllAppointments();
            appointments.removeIf(app -> app.getAppointmentId() == selectedAppointment.getAppointmentId());

            selectedAppointment.setTitle(titleInput.getText());
            selectedAppointment.setDescription(descriptionInput.getText());
            selectedAppointment.setLocation(locationInput.getText());
            selectedAppointment.setContactId(contactId);
            selectedAppointment.setType(typeInput.getText());
            selectedAppointment.setStart(startDateTime);
            selectedAppointment.setEnd(endDateTime);
            selectedAppointment.setCustomerId(customerId);
            selectedAppointment.setUserId(userId);

            AppointmentDAO.update(selectedAppointment);
            Alerts.alertInfo(1,"");
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
        } catch (SQLException e) {
            Alerts.alertError(9);
        }
    }

    /**
     * Alerts the use if they are sure if they want to cancel adding an appointment. If so closes the Stage.
     */
    @FXML
    void onCancel(){
        if(Alerts.alertConfirm(1)){
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        }
    }

}

