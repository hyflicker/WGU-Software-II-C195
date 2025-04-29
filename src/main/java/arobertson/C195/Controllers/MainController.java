package arobertson.C195.Controllers;

import arobertson.C195.Utilities.Alerts;
import arobertson.C195.DAO.AppointmentDAO;
import arobertson.C195.DAO.ContactDAO;
import arobertson.C195.DAO.CustomerDAO;
import arobertson.C195.DAO.LocationDAO;
import arobertson.C195.Models.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Controls the functionality of the "Main.fxml" report.
 */
public class MainController implements Initializable {

    @FXML
    private RadioButton allRadioBtn;
    @FXML
    private Button appAdd;
    @FXML
    private TableColumn<Appointment, String> appContractColumn;
    @FXML
    private TableColumn<Appointment, Integer> appCustomerIdColumn;
    @FXML
    private Button appDelete;
    @FXML
    private TableColumn<Appointment, String> appDescriptionColumn;
    @FXML
    private TableColumn<Appointment, LocalDateTime> appEndDateTimeColumn;
    @FXML
    private TableColumn<Application, Integer> appIdColumn;
    @FXML
    private TableColumn<Appointment, String> appLocationColumn;
    @FXML
    private TableColumn<Appointment, LocalDateTime> appStartDateTimeColumn;
    @FXML
    private TableColumn<Appointment, String> appTitleColumn;
    @FXML
    private TableColumn<Appointment, String> appTypeColumn;
    @FXML
    private Button appUpdate;
    @FXML
    private TableColumn<Appointment, Integer> appUserIdColumn;
    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private Button customerAdd;
    @FXML
    private TableColumn<?, ?> customerAddressColumn;
    @FXML
    private Button customerDelete;
    @FXML
    private TableColumn<?, ?> customerIdColumn;

    @FXML
    private TableColumn<?, ?> customerNameColumn;

    @FXML
    private TableColumn<Customer, String> customerPhoneNumberColumn;

    @FXML
    private TableColumn<?, ?> customerPostalCodeColumn;

    @FXML
    private TableColumn<Customer, String> customerStateColumn;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private Button customerUpdate;

    @FXML
    private Label customersLabel;

    @FXML
    private Button logoutBtn;

    @FXML
    private RadioButton monthRadioBtn;

    @FXML
    private ToggleGroup radioFilter;

    @FXML
    private Button reportsBtn;

    @FXML
    private RadioButton weekRadioBtn;

    /**
     * Loads the "Add Appointment.fxml" when the Add button is pressed.
     */
    @FXML
    void addAppointment() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/arobertson/c195/views/Add Appointment.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Appointment");
            stage.setScene(new Scene(root));
            stage.resizableProperty().setValue(false);
            stage.setOnCloseRequest((e)->{
                e.consume();
                if(Alerts.alertConfirm(1)){
                    stage.close();
                }
            });
            stage.showAndWait();
            refreshAppointmentTable();
        } catch (IOException e) {
            Alerts.alertError(5);
        }
    }

    /**
     * Loads the "Add Customer.fxml" when the Add button is pressed.
     */
    @FXML
    void addCustomer() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/arobertson/c195/views/Add Customer.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Customer");
            stage.setScene(new Scene(root));
            stage.resizableProperty().setValue(false);
            stage.setOnCloseRequest((e)->{
                e.consume();
                if(Alerts.alertConfirm(1)){
                    stage.close();
                }
            });
            stage.showAndWait();
            refreshCustomerTable();
        } catch (IOException e) {
            Alerts.alertError(5);
        }
    }

    /**
     * Deletes the appointment selected in the appointmentsTable. If no appointment is selected, it alerts the user.
     */
    @FXML
    void deleteAppointment() {
        Appointment selected = appointmentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alerts.alertWarning(3);
            return;
        }

        if (!Alerts.alertConfirm(2)) {
            return;
        }

        try {
            int appId = selected.getAppointmentId();
            AppointmentDAO.delete(appId);
            refreshAppointmentTable();
            String appInfo = "Appointment Id: " + appId + "\nAppointment Type: " + selected.getType();
            Alerts.alertInfo(3,appInfo);
        } catch (SQLException e) {
            Alerts.alertError(12);
        }
    }

    /**
     * Deletes the customer selected in the customerTable. If a customer is not selected it alerts the user.
     */
    @FXML
    void deleteCustomer() {
        Customer selected = customerTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alerts.alertWarning(4);
            return;
        }

        try {
            if (!CustomerDAO.appointmentListIsEmpty(selected.getCustomerId())) {
                Alerts.alertError(13);
                return;
            }

            if (!Alerts.alertConfirm(2)) {
                return;
            }

            CustomerDAO.deleteCustomer(selected.getCustomerId());
            refreshCustomerTable();
            Alerts.alertInfo(6,"");
        } catch (SQLException e) {
            Alerts.alertError(12);
        }
    }

    /**
     * Alerts the user to confirm if they want to logout. If yes, it closes the Main stage and loads the login stage.
     * @throws IOException If an error occurs it throws the error.
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    @FXML
    void logout() throws IOException, SQLException {
        Alerts.alertLogout((Stage) logoutBtn.getScene().getWindow());
    }

    /**
     * When the Contract Schedule is selected in reports, it loads the Contact Schedule Report.
     */
    @FXML
    void onContactSchedule(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/arobertson/C195/views/Contact Schedule.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Report - Contact Schedule");
            stage.setScene(new Scene(root));
            stage.resizableProperty().setValue(false);
            stage.setOnCloseRequest((e)->{
                e.consume();
                if(Alerts.alertConfirm(1)){
                    stage.close();
                }
            });
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * When the Customer Appointment Totals is selected in reports, it loads the Customer Appointment Totals report.
     */
    @FXML
    void onCustomerAppointmentTotals(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/arobertson/C195/views/Customer Appointment Totals.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Report - Customer Appointment Totals");
            stage.setScene(new Scene(root));
            stage.resizableProperty().setValue(false);
            stage.setOnCloseRequest((e)->{
                e.consume();
                if(Alerts.alertConfirm(1)){
                    stage.close();
                }
            });
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * When the Total Appointments By Country is selected in reports, it loads the Appointments By Country report.
     */
    @FXML
    void onTotalAppointmentsByCountry(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/arobertson/C195/views/Appointments By Country.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Report - Appointments By Country");
            stage.setScene(new Scene(root));
            stage.resizableProperty().setValue(false);
            stage.setOnCloseRequest((e)->{
                e.consume();
                if(Alerts.alertConfirm(1)){
                    stage.close();
                }
            });
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * When an appointment in the appointment table is selected it opens the Update Appointment and loads the data into the Update Appointment view. If not appointment is selected, it alerts the user.
     */
    @FXML
    void updateAppointment() {
        Appointment selected = appointmentTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            Alerts.alertWarning(1);
            return;
        }
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/arobertson/C195/views/Update Appointment.fxml"));
            Parent root = loader.load();

            UpdateAppointmentController controller = loader.getController();
            controller.setAppointment(selected);

            Stage stage = new Stage();
            stage.setTitle("Update Appointment");
            stage.setScene(new Scene(root));
            stage.resizableProperty().setValue(false);
            stage.setOnCloseRequest((e)->{
                e.consume();
                if(Alerts.alertConfirm(1)){
                    stage.close();
                }
            });
            stage.showAndWait();
            refreshAppointmentTable();
        } catch (IOException e) {
            Alerts.alertError(6);
        }
    }

    /**
     * When a customer in the customer table is selected it opens the Update Customer and loads the data into the Update Customer view. If no customer is selected, it alerts the user.
     */
    @FXML
    void updateCustomer() {
        Customer selected = customerTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            Alerts.alertWarning(2);
            return;
        }
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/arobertson/C195/views/Update Customer.fxml"));
            Parent root = loader.load();

            UpdateCustomerController controller = loader.getController();
            controller.setCustomer(selected);

            Stage stage = new Stage();
            stage.setTitle("Update Customer");
            stage.setScene(new Scene(root));
            stage.resizableProperty().setValue(false);
            stage.setOnCloseRequest((e)->{
                e.consume();
                if(Alerts.alertConfirm(1)){
                    stage.close();
                }
            });
            stage.showAndWait();
            refreshCustomerTable();
        } catch (IOException e) {
            Alerts.alertError(6);
        }
    }

    /**
     * When the radio button for current month is selected it loads all the appointments for this month.
     */
    @FXML
    void currentMonthApps(){
        try{
            appointmentTable.setItems(AppointmentDAO.getAppointmentsThisMonth());
        } catch (SQLException e) {
            Alerts.alertError(4);
        }
    }

    /**
     * When the radio button for current week is selected it loads all the appointments for this week.
     */
    @FXML
    void currentWeekApps(){
        try{
            appointmentTable.setItems(AppointmentDAO.getAppointmentsThisWeek());
        } catch (SQLException e) {
            Alerts.alertError(4);
        }
    }
    /**
     * When the radio button for all appointments is selected it loads all the appointments regardless of when.
     */
    @FXML
    void allAppointments(){
        try{
            appointmentTable.setItems(AppointmentDAO.getAllAppointments());
        } catch (SQLException e) {
            Alerts.alertError(4);
        }
    }

    /**
     * Initialize and sets all the cells for both tables and calls refresh functions to load the data in the tables.
     * @param url - Not Used
     * @param lang - Not Used
     */
    @Override
    public void initialize(URL url, ResourceBundle lang) {
        //Initialize appointment table columns
        appIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        appDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        appLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        appContractColumn.setCellValueFactory(data -> {
            try {
                Contact contact = ContactDAO.getContact(data.getValue().getContactId());
                return new SimpleStringProperty(contact != null ? contact.getContactName() : "");
            } catch (SQLException e) {
                return new SimpleStringProperty("");
            }
        });
        appTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        appStartDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        appEndDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        appCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

        // Initialize customer table columns
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        customerStateColumn.setCellValueFactory(data -> {
            try {
                FirstLevelDivision division = LocationDAO.getDivision(data.getValue().getDivisionId());
                if (division != null) {
                    Country country = LocationDAO.getCountry(division.getCountryId());
                    return new SimpleStringProperty(division.getDivisionName() +
                            (country != null ? ", " + country.getCountry() : ""));
                }
                return new SimpleStringProperty("");
            } catch (SQLException e) {
                return new SimpleStringProperty("");
            }
        });
        customerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        // Load initial data to tables
        refreshAppointmentTable();
        refreshCustomerTable();
    }

    /**
     * Loads and/or refreshes the data into the appointment table.
     */
    private void refreshAppointmentTable() {
        try {
            ObservableList<Appointment> appointments = AppointmentDAO.getAllAppointments();
            appointmentTable.setItems(appointments);
            System.out.print("App Table Refresh");
        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    /**
     * Loads and/or refreshes the data into the customer table.
     */
    private void refreshCustomerTable() {
        try {
            ObservableList<Customer> customers = CustomerDAO.getCustomerList();
            customerTable.setItems(customers);
        } catch (SQLException e) {
            System.out.print(e);
//            showAlert("Error", "Could not refresh customer data", Alert.AlertType.ERROR);
        }
    }
}
