package arobertson.C195.Controllers;

import Utilities.InputLoader;
import arobertson.C195.DAO.AppointmentDAO;
import arobertson.C195.DAO.ContactDAO;
import arobertson.C195.DAO.CustomerDAO;
import arobertson.C195.Models.Appointment;
import arobertson.C195.Models.Contact;
import arobertson.C195.Models.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContactScheduleController implements Initializable {

    @FXML
    private TableColumn<?, ?> appointmentIdColumn;

    @FXML
    private TableView<Appointment> contactScheduleTable;

    @FXML
    private ComboBox<String> contactSelector;

    @FXML
    private TableColumn<Customer, String> customerIdColumn;

    @FXML
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    private TableColumn<?, ?> endColumn;

    @FXML
    private TableColumn<?, ?> startColumn;

    @FXML
    private TableColumn<?, ?> titleColumn;

    @FXML
    private TableColumn<?, ?> typeColumn;



    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        try {
            loadContacts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadContacts() throws SQLException {
        new InputLoader().loadContacts(contactSelector);
        if (!contactSelector.getItems().isEmpty()) {
            contactSelector.getSelectionModel().selectFirst();
            loadContactAppointments();
        }
    }

    @FXML
    void loadContactAppointments() {
        int contactId = Integer.parseInt(contactSelector.getValue().split(" - ")[0].trim());
        if (contactSelector != null) {
            try {
                ObservableList<Appointment> schedule = AppointmentDAO.getAppointmentsByContactId(contactId);
                contactScheduleTable.setItems(schedule);
            } catch (SQLException e) {
                System.out.print(e);
                //NEED TO ENTER ERROR MESSAGE
            }
        }
    }

}
