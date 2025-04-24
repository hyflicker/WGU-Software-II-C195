package arobertson.C195.DAO;

import Utilities.Database;
import arobertson.C195.Models.Country;
import arobertson.C195.Models.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationDAO {
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
