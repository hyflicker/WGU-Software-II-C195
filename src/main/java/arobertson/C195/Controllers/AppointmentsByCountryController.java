package arobertson.C195.Controllers;

import Utilities.InputLoader;
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

    @FXML
    void loadAppointmentsInCountry() throws SQLException {
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        totalAppointmentsByCountryTable.setItems(LocationDAO.getTotalAppointmentsByCountry());
    }

    @FXML
    void loadCountrySelector() throws SQLException{
        new InputLoader().loadCountries(countrySelector);
        if(!countrySelector.getItems().isEmpty()){
            countrySelector.getSelectionModel().selectFirst();
            loadAppointmentsByCountry();
        }
    }

    @FXML
    void loadAppointmentsByCountry() throws SQLException{
        int countryId = LocationDAO.getCountryIdByName(countrySelector.getValue());
        if (countrySelector != null) {
            try {
                ObservableList<Appointment> apps = AppointmentDAO.getAppointmentsByCountryId(countryId);
                appointmentsByCountryTable.setItems(apps);
            } catch (SQLException e) {
                System.out.print(e);
                //NEED TO ENTER ERROR MESSAGE
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
