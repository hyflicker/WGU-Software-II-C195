package arobertson.C195.Controllers;

import arobertson.C195.Utilities.InputLoader;
import arobertson.C195.DAO.AppointmentDAO;
import arobertson.C195.Models.Appointment;
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
 * Controls the functionality of the "Customer Appointment Totals.fxml" report.
 */
public class CustomerAppointmentTotalsController implements Initializable {

    @FXML
    private TableView<Appointment> customerAppointmentTotalsTable;

    @FXML
    private TableColumn<Appointment, String> typeColumn;

    @FXML
    private TableColumn<Appointment, Integer> totalAppointmentsColumn;

    @FXML
    private ComboBox<String> monthSelector;

    /**
     * Initializes and loads data into the monthSelector.
     * @param url - Not Used
     * @param lang - Not Used
     */
    @Override
    public void initialize(URL url, ResourceBundle lang) {
        try {
            new InputLoader().loadMonths(monthSelector);
            loadCustomerTotals();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param month - a String value that will be used in the switch statement to return the integer value of the month.
     * @return - returns a int value of the month.
     */
    private int getMonthNumber(String month) {
        return switch (month) {
            case "January" -> 1;
            case "February" -> 2;
            case "March" -> 3;
            case "April" -> 4;
            case "May" -> 5;
            case "June" -> 6;
            case "July" -> 7;
            case "August" -> 8;
            case "September" -> 9;
            case "October" -> 10;
            case "November" -> 11;
            case "December" -> 12;
            default -> 0; // Or throw an IllegalArgumentException for an invalid month
        };
    }

    /**
     * Sets the cell value of the table and loads the data into the table.
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public void loadCustomerTotals() throws SQLException {
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        totalAppointmentsColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        customerAppointmentTotalsTable.setItems(AppointmentDAO.getCustomerAppTotals(getMonthNumber(monthSelector.getValue())));
    }
}
