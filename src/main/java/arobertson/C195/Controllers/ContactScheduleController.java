package arobertson.C195.Controllers;

import arobertson.C195.Utilities.InputLoader;
import arobertson.C195.DAO.AppointmentDAO;
import arobertson.C195.Models.Appointment;
import arobertson.C195.Models.Customer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controls the functionality of the "Contact Schedule.fxml" report.
 */
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


    /**
     * Initializes and set the cell values of the table. Calls the loadContact function to load the data into the table.
     * @param url Not Used
     * @param lang Not Used
     */
    public void initialize(URL url, ResourceBundle lang) {
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

    /**
     * Loads the data into the contactSelector ComboBox
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    private void loadContacts() throws SQLException {
        new InputLoader().loadContacts(contactSelector);
        if (!contactSelector.getItems().isEmpty()) {
            contactSelector.getSelectionModel().selectFirst();
            loadContactAppointments();
        }
    }

    /**
     * Loads the data into the contactScheduleTable based on the value of the contactSelector.
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    @FXML
    void loadContactAppointments() throws SQLException{
        int contactId = Integer.parseInt(contactSelector.getValue().split(" - ")[0].trim());
        if (contactSelector != null) {
            ObservableList<Appointment> schedule = AppointmentDAO.getAppointmentsByContactId(contactId);
            contactScheduleTable.setItems(schedule);
        }
    }

}
