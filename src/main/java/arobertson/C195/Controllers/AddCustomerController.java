package arobertson.C195.Controllers;

import Utilities.Alerts;
import Utilities.InputLoader;
import Utilities.Validation;
import arobertson.C195.DAO.CustomerDAO;
import arobertson.C195.DAO.LocationDAO;
import arobertson.C195.Models.Customer;
import arobertson.C195.Models.FirstLevelDivision;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

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

    @FXML
    void onSave(){
        if(!Validation.validateCustomerInputs(nameInput,addressInput,postalCodeInput,phoneNumberInput,countryInput,stateInput)){
            return;
        }
        try {
            int divisionId = -1;
            for(FirstLevelDivision division : LocationDAO.getAllDivisions()){
                if(division.getDivisionName().equals(stateInput.getValue())){
                    divisionId = division.getDivisionId();
                    break;
                }
            }

            Customer newCustomer = new Customer(0, nameInput.getText(), addressInput.getText(), postalCodeInput.getText(), phoneNumberInput.getText(), divisionId);
            CustomerDAO.insertCustomer(newCustomer);

            Alerts.alertInfo(4);
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
        } catch (SQLException e) {
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

   @FXML
   void onCountrySelection() throws SQLException {
        new InputLoader().loadStates(countryInput.getValue(),stateInput);
   }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            new InputLoader().loadCountries(countryInput);
            stateInput.setPromptText("Select a Country First");

        } catch (SQLException e) {
            Alerts.alertError(11);
        }
    }
}
