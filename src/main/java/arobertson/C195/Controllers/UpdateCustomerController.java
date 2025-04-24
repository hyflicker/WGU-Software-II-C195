package arobertson.C195.Controllers;

import Utilities.Alerts;
import Utilities.InputLoader;
import Utilities.Validation;
import arobertson.C195.DAO.CustomerDAO;
import arobertson.C195.DAO.LocationDAO;
import arobertson.C195.Models.Country;
import arobertson.C195.Models.Customer;
import arobertson.C195.Models.FirstLevelDivision;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateCustomerController implements Initializable {

    @FXML
    private TextField addressInput;

    @FXML
    private Label addressLabel;

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<String> countryInput;

    @FXML
    private Label countryLabel;

    @FXML
    private TextField idInput;

    @FXML
    private Label idLabel;

    @FXML
    private TextField nameInput;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField phoneNumberInput;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private TextField postalCodeInput;

    @FXML
    private Label postalCodeLabel;

    @FXML
    private Button saveBtn;

    @FXML
    private ComboBox<String> stateInput;

    @FXML
    private Label stateLabel;

    private Customer selectedCustomer;

    public void initialize(URL url, ResourceBundle rb) {
        try {
            new InputLoader().loadCountries(countryInput);
            
        } catch (SQLException e) {
            Alerts.alertError(11);
        }
    }

    public void setCustomer(Customer customer) {
        this.selectedCustomer = customer;
        populateFields();
    }

    private void populateFields() {
        try {
            idInput.setText(String.valueOf(selectedCustomer.getCustomerId()));
            nameInput.setText(selectedCustomer.getCustomerName());
            addressInput.setText(selectedCustomer.getAddress());
            phoneNumberInput.setText(selectedCustomer.getPhoneNumber());
            postalCodeInput.setText(selectedCustomer.getPostalCode());

            FirstLevelDivision state = LocationDAO.getDivision(selectedCustomer.getDivisionId());
            if (state != null) {
                Country country = LocationDAO.getCountry(state.getCountryId());
                if (country != null) {
                    countryInput.setValue(country.getCountry());
                    stateInput.setValue(state.getDivisionName());
                }
            }

        } catch (SQLException e) {

            Alerts.alertError(14);
        }
    }

    @FXML
    void onSave(){
       try {
           if(!Validation.validateCustomerInputs(nameInput,addressInput,postalCodeInput,phoneNumberInput,countryInput,stateInput)){
               return;
           }

           FirstLevelDivision state = LocationDAO.getAllDivisions().filtered(s -> s.getDivisionName().equals(stateInput.getValue())).get(0);
           selectedCustomer.setCustomerName(nameInput.getText());
           selectedCustomer.setAddress(addressInput.getText());
           selectedCustomer.setPhoneNumber(phoneNumberInput.getText());
           selectedCustomer.setPostalCode(postalCodeInput.getText());
           selectedCustomer.setDivisionId(state.getDivisionId());

           CustomerDAO.updateCustomer(selectedCustomer);

           Alerts.alertInfo(5);
           Stage stage = (Stage) saveBtn.getScene().getWindow();
           stage.close();
       }catch (SQLException e){
           Alerts.alertError(14);
       }
    }

    @FXML
    void onCancel(){
        if(Alerts.alertConfirm(1)){
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        }
    }
}

