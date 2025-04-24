package arobertson.C195.Controllers;
import Utilities.Alerts;
import Utilities.InputLoader;
import Utilities.Validation;
import arobertson.C195.DAO.AppointmentDAO;
import arobertson.C195.Models.Appointment;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
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

    @FXML
    void onSave() throws SQLException{
        if(!Validation.validateAppointmentInputes(titleInput,descriptionInput,locationInput,contactInput, typeInput,startDateInput,startTimeInput,endDateInput,endtTimeInput,customerIdInput,userIdInput)){
            return;
        }

        try{
            int contactId = Integer.parseInt(contactInput.getValue().split(" - ")[0].trim());
            int customerId = Integer.parseInt(customerIdInput.getValue().split(" - ")[0].trim());
            int userId = Integer.parseInt(userIdInput.getValue().split(" - ")[0].trim());

            LocalDateTime startDateTime = LocalDateTime.of(startDateInput.getValue(),startTimeInput.getValue());
            LocalDateTime endDateTime = LocalDateTime.of(endDateInput.getValue(),endtTimeInput.getValue());

            if(!Validation.validateAppointmentTime(AppointmentDAO.getAllAppointments(),customerId,startDateTime,endDateTime)){
                Alerts.alertError(8);
                return;
            }

            Appointment newAppointment = new Appointment(
                    0, // ID will be auto-generated
                    customerId,
                    userId,
                    contactId,
                    titleInput.getText(),
                    descriptionInput.getText(),
                    locationInput.getText(),
                    typeInput.getText(),
                    startDateTime,
                    endDateTime
            );

            AppointmentDAO.insert(newAppointment);
            Alerts.alertInfo(1);
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
        } catch (SQLException e) {
            Alerts.alertError(9);
        }
    }

    @FXML
    void onCancel(){
        if(Alerts.alertConfirm(1)){
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle lang){
        try {
            new InputLoader().loadContacts(contactInput);
            new InputLoader().loadTimes(startTimeInput,endtTimeInput);
            new InputLoader().loadCustomers(customerIdInput);
            new InputLoader().loadUsers(userIdInput);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
