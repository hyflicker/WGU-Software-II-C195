package arobertson.C195.Models;

/**
 * FirstLevelDivision Class - Creates FirstLevelDivision object and adds getters and setters.
 */
public class FirstLevelDivision {
    private int divisionId;
    private String divisionName;
    private int countryId;

    /**
     * Constructs a FirstLevelDivision object with division details.
     *
     * @param divisionId   The unique ID of the division.
     * @param divisionName The name of the division.
     * @param countryId    The ID of the associated country.
     */
    public FirstLevelDivision(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /**
     * Gets the division ID.
     *
     * @return The division ID.
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Sets the division ID.
     *
     * @param divisionId The division ID to set.
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * Gets the division name.
     *
     * @return The division name.
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * Sets the division name.
     *
     * @param divisionName The division name to set.
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * Gets the country ID.
     *
     * @return The country ID.
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets the country ID.
     *
     * @param countryId The country ID to set.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
