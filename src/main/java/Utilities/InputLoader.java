package Utilities;
import arobertson.C195.DAO.ContactDAO;
import arobertson.C195.DAO.LocationDAO;
import arobertson.C195.DAO.UserDAO;
import arobertson.C195.Models.Country;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class responsible for loading data into various input controls (ComboBoxes, DatePickers).
 */
public class InputLoader {
    /**
     * Loads all contacts from the database into the provided ComboBox.
     * The contact information is displayed in the format "ContactID - ContactName".
     *
     * @param contactInput The ComboBox to populate with contact data.
     * @throws SQLException If a database access error occurs.
     */
    public void loadContacts(ComboBox<String> contactInput) throws SQLException {
        contactInput.getItems().clear();
        // Lambda expression for concisely iterating through contacts and adding them to the ComboBox.
        // This is more readable and less verbose than a traditional for-each loop, especially for simple operations.
        ContactDAO.getAllContacts().forEach(contact ->
                contactInput.getItems().add(contact.getContactId() + " - " + contact.getContactName())
        );
    }

    /**
     * Loads a list of available appointment times into the provided start and end time ComboBoxes.
     * The times are generated based on the business hours of 8:00 AM to 10:00 PM Eastern Time (EST),
     * converted to the user's local time zone, and presented in 30-minute intervals.
     * The time display is formatted as "h:mm a" (e.g., "8:00 AM").
     *
     * @param startTimeInput The ComboBox to populate with available start times.
     * @param endTimeInput   The ComboBox to populate with available end times.
     */
    @FXML
    public void loadTimes(ComboBox<LocalTime> startTimeInput, ComboBox<LocalTime> endTimeInput) {
        startTimeInput.getItems().clear();
        endTimeInput.getItems().clear();

        ZoneId estZone = ZoneId.of("America/New_York"); // EST Time Zone
        ZoneId localZone = ZoneId.systemDefault(); // User's local time zone

        LocalTime estStartTime = LocalTime.of(8, 0);
        LocalTime estEndTime = LocalTime.of(22, 0);
        LocalTime currentTimeEst = estStartTime;

        while (!currentTimeEst.isAfter(estEndTime)) {
            // Combine LocalTime with the EST ZoneId for today's date
            ZonedDateTime zonedDateTimeEstToday = ZonedDateTime.of(java.time.LocalDate.now(), currentTimeEst, estZone);

            // Convert to the local time zone
            ZonedDateTime localZonedDateTimeToday = zonedDateTimeEstToday.withZoneSameInstant(localZone);

            // Extract the LocalTime from the local ZonedDateTime
            LocalTime localTime = localZonedDateTimeToday.toLocalTime();

            startTimeInput.getItems().add(localTime);
            endTimeInput.getItems().add(localTime);

            currentTimeEst = currentTimeEst.plusMinutes(30);
        }

        // Set a custom String converter for better display
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        startTimeInput.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(LocalTime time) {
                return (time != null) ? formatter.format(time) : "";
            }

            @Override
            public LocalTime fromString(String string) {
                return (string != null && !string.isEmpty()) ? LocalTime.parse(string, formatter) : null;
            }
        });

        endTimeInput.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(LocalTime time) {
                return (time != null) ? formatter.format(time) : "";
            }

            @Override
            public LocalTime fromString(String string) {
                return (string != null && !string.isEmpty()) ? LocalTime.parse(string, formatter) : null;
            }
        });
    }

    /**
     * Loads all contacts from the database into the provided ComboBox for customer selection.
     * The contact information is displayed in the format "ContactID - ContactName".
     * Note: This method currently uses {@link ContactDAO} and displays contact information,
     * which might be unintended if customer names are expected. Consider reviewing the data source.
     *
     * @param customerIdInput The ComboBox to populate with customer identifiers.
     * @throws SQLException If a database access error occurs.
     */
    public void loadCustomers(ComboBox<String> customerIdInput) throws SQLException{
        customerIdInput.getItems().clear();
        // Lambda expression provides a concise way to process each contact retrieved from the database.
        // An alternative would be a traditional for loop, which would involve more boilerplate code.
        ContactDAO.getAllContacts().forEach(customer ->
                customerIdInput.getItems().add(customer.getContactId() + " - " + customer.getContactName())
        );
    }

    /**
     * Loads all users from the database into the provided ComboBox.
     * The user information is displayed in the format "UserID - Username".
     *
     * LAMBDA #1 - Using a lambda expression here makes the code more succinct and easier to read compared to a traditional loop.
     * It clearly expresses the intent: for each user, add their formatted ID and username to the ComboBox.
     * @param userIdInput The ComboBox to populate with user data.
     * @throws SQLException If a database access error occurs.
     */
    public void loadUsers(ComboBox<String> userIdInput) throws SQLException{
        userIdInput.getItems().clear();
        //
        UserDAO.getAllUsers().forEach(user -> userIdInput.getItems().add(user.getUserId() + " - " + user.getUsername()));
    }

    /**
     * Loads all countries from the database into the provided ComboBox.
     *
     * @param countryInput The ComboBox to populate with country names.
     * @throws SQLException If a database access error occurs.
     */
    public void loadCountries(ComboBox<String> countryInput) throws SQLException{
        // The lambda expression simplifies the process of iterating through the list of countries
        // and adding each country name to the ComboBox's items. This is more direct than a standard loop.
        LocationDAO.getAllCountries().forEach(country ->
                countryInput.getItems().add(country.getCountry())
        );
    }

    /**
     * Loads the divisions (states/provinces) associated with the selected country into the provided ComboBox.
     * If no country is selected ({@code countryInput} is null), the state ComboBox will be cleared.
     * This lambda expression efficiently iterates through the divisions and adds their names to the ComboBox.
     * Without it,a for-each loop would be necessary.
     *
     * @param countryInput The currently selected country name.
     * @param stateInput   The ComboBox to populate with state/province names.
     * @throws SQLException If a database access error occurs.
     */
    public void loadStates(String countryInput, ComboBox<String> stateInput) throws SQLException{
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

    /**
     * Disables selection of weekend days (Saturday and Sunday) in the provided DatePicker controls.
     * This is achieved by setting a custom day cell factory for both the start and end date pickers.
     *
     * @param startDateInput The DatePicker for the start date.
     * @param endDateInput   The DatePicker for the end date.
     */
    public void diableWeekends(DatePicker startDateInput, DatePicker endDateInput){
        // Set the day cell factory for startDateInput
        startDateInput.setDayCellFactory(datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setDisable(true);
                } else {
                    DayOfWeek dayOfWeek = date.getDayOfWeek();
                    if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                        setDisable(true);
                        setStyle("-fx-background-color: #d3d3d3;"); // Optional: Grey out weekends
                    }
                }
            }
        });

        // Set the day cell factory for endDateInput
        endDateInput.setDayCellFactory(datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setDisable(true);
                } else {
                    DayOfWeek dayOfWeek = date.getDayOfWeek();
                    if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                        setDisable(true);
                        setStyle("-fx-background-color: #d3d3d3;"); // Optional: Grey out weekends
                    }
                }
            }
        });
    }

    /**
     * Loads a list of month names into the provided ComboBox.
     * The months are listed in chronological order, starting with January.
     * The "January" is set as the default selected value.
     *
     * @param monthSelector The ComboBox to populate with month names.
     */
    public void loadMonths(ComboBox<String> monthSelector){
        List<String> months = Arrays.asList(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );
        monthSelector.setItems(FXCollections.observableArrayList(months));
        // Set a default value if needed
        monthSelector.setValue("January");
    }
}