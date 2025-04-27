package arobertson.C195.Models;

import java.time.LocalDateTime;

/**
 * Country Class - Creates country object and adds getters and setters.
 */
public class Country {
    private int countryId;
    private String country;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    private int count;

    /**
     * Constructor for Country class with all attributes.
     * @param countryId The ID of the country.
     * @param country The name of the country.
     * @param createdDate The date and time the country record was created.
     * @param createdBy The user who created the country record.
     * @param lastUpdated The date and time the country record was last updated.
     * @param lastUpdatedBy The user who last updated the country record.
     */
    public Country(int countryId, String country, LocalDateTime createdDate, String createdBy, LocalDateTime lastUpdated, String lastUpdatedBy) {
        this.countryId = countryId;
        this.country = country;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Constructor for Country class with ID and name.
     * @param countryId The ID of the country.
     * @param country The name of the country.
     */
    public Country(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    /**
     * Constructor for Country class with ID, name, and count.
     * @param countryId The ID of the country.
     * @param country The name of the country.
     * @param count The count.
     */
    public Country(int countryId, String country, int count){
        this.countryId = countryId;
        this.country = country;
        this.count = count;
    }

    /**
     * Gets the country ID.
     * @return The country ID.
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets the country ID.
     * @param countryId The country ID to set.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Gets the country name.
     * @return The country name.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country name.
     * @param country The country name to set.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the creation date of the country record.
     * @return The creation date.
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the creation date of the country record.
     * @param createdDate The creation date to set.
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the user who created the country record.
     * @return The creator's username.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user who created the country record.
     * @param createdBy The creator's username to set.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the last updated date and time of the country record.
     * @return The last updated date and time.
     */
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets the last updated date and time of the country record.
     * @param lastUpdated The last updated date and time to set.
     */
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * Gets the user who last updated the country record.
     * @return The username of the last updater.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the user who last updated the country record.
     * @param lastUpdatedBy The username of the last updater to set.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Gets the  count.
     * @return The count.
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the count.
     * @param count The count to set.
     */
    public void setCount(int count) {
        this.count = count;
    }
}
