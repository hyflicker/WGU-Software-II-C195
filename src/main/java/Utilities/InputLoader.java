package Utilities;
import arobertson.C195.DAO.ContactDAO;
import arobertson.C195.DAO.LocationDAO;
import arobertson.C195.DAO.UserDAO;
import arobertson.C195.Models.Country;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.sql.SQLException;
import java.time.LocalTime;

public class InputLoader {
    public void loadContacts(ComboBox<String> contactInput) throws SQLException {
        contactInput.getItems().clear();
        ContactDAO.getAllContacts().forEach(contact ->
                contactInput.getItems().add(contact.getContactId() + " - " + contact.getContactName())
        );
    }
    @FXML
    public void loadTimes(ComboBox<LocalTime> startTimeInput,ComboBox<LocalTime> endtTimeInput) {
        startTimeInput.getItems().clear();
        endtTimeInput.getItems().clear();

        LocalTime time = LocalTime.of(8, 0);
        while (!time.isAfter(LocalTime.of(22, 0))) {
            startTimeInput.getItems().add(time);
            endtTimeInput.getItems().add(time);
            time = time.plusMinutes(30);
        }
    }

    public void loadCustomers(ComboBox<String> customerIdInput) throws SQLException{
        customerIdInput.getItems().clear();
        ContactDAO.getAllContacts().forEach(customer ->
                customerIdInput.getItems().add(customer.getContactId() + " - " + customer.getContactName())
        );
    }

    public void loadUsers(ComboBox<String> userIdInput) throws SQLException{
        userIdInput.getItems().clear();
        UserDAO.getAllUsers().forEach(user -> {
            userIdInput.getItems().add(user.getUserId() + " - " + user.getUsername());
        });
    }
    public void loadCountries(ComboBox<String> countryInput) throws SQLException{
        LocationDAO.getAllCountries().forEach(country ->
                countryInput.getItems().add(country.getCountry())
        );
    }

    public void loadStates(String countryInput,ComboBox<String> stateInput) throws SQLException{
        stateInput.getItems().clear();
        if(countryInput != null){
            int countryId = -1;
            for (Country country : LocationDAO.getAllCountries()) {
                if (country.getCountry().equals(countryInput)) {
                    countryId = country.getCountryId();
                    break;
                }
            }

            LocationDAO.getDivisionsByCountry(countryId).forEach(state ->
                    stateInput.getItems().add(state.getDivisionName())

            );
            stateInput.setPromptText("");
        }
    }

}
