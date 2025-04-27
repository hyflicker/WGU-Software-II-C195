package arobertson.C195.DAO;

import arobertson.C195.Utilities.Database;
import arobertson.C195.Models.Country;
import arobertson.C195.Models.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Location Database Access Object. Functions in this class make up different ways of returning location data.
 */
public class LocationDAO {
    /**
     * Returns a list of all Countries structured by the Country class.
     */
    public static ObservableList<Country> getAllCountries() throws SQLException {
        ObservableList<Country> countries = FXCollections.observableArrayList();
        String sql = "SELECT Country_Id, Country FROM countries";

        try (PreparedStatement ps = Database.connection.prepareStatement(sql)) {
            ResultSet results = ps.executeQuery();

            while (results.next()) {
                Country country = new Country(results.getInt("Country_Id"), results.getString("Country"));
                countries.add(country);
            }
        }
        return countries;
    }

    /**
     * Returns a list of Divisions based on the provided countryId.
     * @param countryId Int value corresponding to a country.
     * @return returns a list of divisions
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static ObservableList<FirstLevelDivision> getDivisionsByCountry(int countryId) throws SQLException {
        ObservableList<FirstLevelDivision> divisions = FXCollections.observableArrayList();
        String sql = "SELECT Division_Id, Division, Country_Id FROM first_level_divisions WHERE Country_Id = ?";

        try (PreparedStatement ps = Database.connection.prepareStatement(sql)) {
            ps.setInt(1, countryId);
            ResultSet results = ps.executeQuery();

            while (results.next()) {
                FirstLevelDivision division = new FirstLevelDivision(
                        results.getInt("Division_Id"),
                        results.getString("Division"),
                        results.getInt("Country_Id")
                );
                divisions.add(division);
            }
        }
        return divisions;
    }

    /**
     * Returns a single country based on the countryId provided.
     * @param countryId Int value corresponding to a country.
     * @return returns a single country.
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static Country getCountry(int countryId) throws SQLException {
        String sql = "SELECT Country_Id, Country FROM countries WHERE Country_Id = ?";

        try (PreparedStatement ps = Database.connection.prepareStatement(sql)) {
            ps.setInt(1, countryId);
            ResultSet results = ps.executeQuery();

            if (results.next()) {
                return new Country(
                        results.getInt("Country_Id"),
                        results.getString("Country")
                );
            }
        }
        return null;
    }

    /**
     * Returns all Divisions
     * @return Returns all Divisions
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static ObservableList<FirstLevelDivision> getAllDivisions() throws SQLException {
        ObservableList<FirstLevelDivision> divisions = FXCollections.observableArrayList();
        String sql = "SELECT Division_Id, Division, Country_Id FROM first_level_divisions";

        try (PreparedStatement ps = Database.connection.prepareStatement(sql)) {
            ResultSet results = ps.executeQuery();

            while (results.next()) {
                FirstLevelDivision division = new FirstLevelDivision(
                        results.getInt("Division_Id"),
                        results.getString("Division"),
                        results.getInt("Country_Id")
                );
                divisions.add(division);
            }
        }
        return divisions;
    }

    /**
     * Returns Division from provided divisionId
     * @param divisionId Int value corresponding to a single division.
     * @return FirstLevelDivision
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static FirstLevelDivision getDivision(int divisionId) throws SQLException {
        String sql = "SELECT Division_Id, Division, Country_Id FROM first_level_divisions WHERE Division_Id = ?";

        try (PreparedStatement ps = Database.connection.prepareStatement(sql)) {
            ps.setInt(1, divisionId);
            ResultSet results = ps.executeQuery();

            if (results.next()) {
                return new FirstLevelDivision(
                        results.getInt("Division_Id"),
                        results.getString("Division"),
                        results.getInt("Country_Id")
                );
            }
        }
        return null;
    }

    /**
     * Returns a list of countries with the total amount of appointments tied to it.
     * @return ObservableList Country
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static ObservableList<Country> getTotalAppointmentsByCountry() throws SQLException{
        ObservableList<Country> countries = FXCollections.observableArrayList();
        String query = "SELECT cy.Country_ID, cy.Country, COUNT(ap.Appointment_ID) AS count FROM countries AS cy LEFT JOIN first_level_divisions AS fld ON cy.Country_ID = fld.Country_ID LEFT JOIN customers AS cu ON fld.Division_ID = cu.Division_ID LEFT JOIN appointments AS ap ON cu.Customer_ID = ap.Customer_ID GROUP BY cy.Country_ID, cy.Country ORDER BY count DESC";
        try (PreparedStatement ps = Database.connection.prepareStatement(query)) {
            ResultSet results = ps.executeQuery();

            while (results.next()) {
                countries.add(new Country(
                        results.getInt("Country_ID"),
                        results.getString("country"),
                        results.getInt("count")
                ));
            }
        }
        return countries;
    }

    /**
     * Returns the ID of a County based on the countryName provided.
     * @param countryName String value of a country.
     * @return Int countryId
     * @throws SQLException If an error occurs with SQL query it throws the error.
     */
    public static int getCountryIdByName(String countryName) throws SQLException{
        String query = "SELECT Country_ID from countries WHERE Country = ?";
        PreparedStatement ps = Database.connection.prepareStatement(query);
        ps.setString(1,countryName);
        ResultSet results = ps.executeQuery();

        if(results.next()){
            return results.getInt("Country_ID");
        }
        return -1;
    }
}
