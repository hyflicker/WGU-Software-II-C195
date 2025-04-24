package arobertson.C195.Controllers;

import arobertson.C195.DAO.CustomerDAO;
import arobertson.C195.Models.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerAppointmentTotalsController implements Initializable {

    @FXML
    private TableView<Customer> customerAppointmentTotalsTable;

    @FXML
    private TableColumn<Customer, String> customerColumn;

    @FXML
    private TableColumn<Customer, Integer> totalAppointmentsColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadCustomerTotals();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerTotals() throws SQLException {
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        totalAppointmentsColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        customerAppointmentTotalsTable.setItems(CustomerDAO.getCustomerAppTotals());
    }
}
