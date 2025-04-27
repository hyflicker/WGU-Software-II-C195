package arobertson.C195.Controllers;

import arobertson.C195.Utilities.InputLoader;
import arobertson.C195.DAO.AppointmentDAO;
import arobertson.C195.DAO.LocationDAO;
import arobertson.C195.Models.Appointment;
import arobertson.C195.Models.Country;
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
 * Controls the functionality of the "AppointmentByCountry.fxml" report.
 */
public class AppointmentsByCountryController implements Initializable {

    @FXML
    private TableColumn<?, ?> appointmentIdColumn;

    @FXML
    private TableView<Appointment> appointmentsByCountryTable;

    @FXML
    private TableColumn<?, ?> countryColumn;

    @FXML
    private ComboBox<String> countrySelector;

    @FXML
    private TableColumn<?, ?> customerIdColumn;

    @FXML
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    private TableColumn<?, ?> endColumn;

    @FXML
    private TableColumn<?, ?> startColumn;

    @FXML
    private TableColumn<?, ?> titleColumn;

    @FXML
    private TableView<Country> totalAppointmentsByCountryTable;

    @FXML
    private TableColumn<?, ?> totalColumn;

    @FXML
    private TableColumn<?, ?> typeColumn;

    /**
     * Loads the totalAppointmentsByCountry table with data depending on the selection of the country.
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    @FXML
    void loadAppointmentsInCountry() throws SQLException {
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        totalAppointmentsByCountryTable.setItems(LocationDAO.getTotalAppointmentsByCountry());
    }

    /**
     * Loads the countries into the countrySelector ComboBox.
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    @FXML
    void loadCountrySelector() throws SQLException{
        new InputLoader().loadCountries(countrySelector);
        if(!countrySelector.getItems().isEmpty()){
            countrySelector.getSelectionModel().selectFirst();
            loadAppointmentsByCountry();
        }
    }

    /**
     * When the countrySelector is changed, it loads the new data into the appointmentsByCountryTable.
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    @FXML
    void loadAppointmentsByCountry() throws SQLException{
        int countryId = LocationDAO.getCountryIdByName(countrySelector.getValue());
        if (countrySelector != null) {
            ObservableList<Appointment> apps = AppointmentDAO.getAppointmentsByCountryId(countryId);
            appointmentsByCountryTable.setItems(apps);
        }

    }

    /**
     * Initializes and sets the cell values of the table and then calls functions to load data into table and selector.
     * @param url - Not Used
     * @param lang - Not Used
     */
    @Override
    public void initialize(URL url, ResourceBundle lang) {
        try {
            appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
            endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
            customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            loadAppointmentsInCountry();
            loadCountrySelector();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
