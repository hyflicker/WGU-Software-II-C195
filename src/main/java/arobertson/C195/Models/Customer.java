package arobertson.C195.Models;

import java.time.LocalDateTime;

/**
 * Customer Class - Creates customer object and adds getters and setters.
 */
public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    private int divisionId;

    /**
     * Constructs a Customer object with full details.
     *
     * @param customerId    The unique ID of the customer.
     * @param customerName  The name of the customer.
     * @param address       The address of the customer.
     * @param postalCode    The postal code of the customer's address.
     * @param phoneNumber     The phone number of the customer.
     * @param createdDate     The date and time the customer record was created.
     * @param createdBy       The user who created the customer record.
     * @param lastUpdated     The date and time the customer record was last updated.
     * @param lastUpdatedBy   The user who last updated the customer record.
     * @param divisionId      The ID of the associated division.
     */
    public Customer(int customerId, String customerName, String address, String postalCode, String phoneNumber, LocalDateTime createdDate, String createdBy, LocalDateTime lastUpdated, String lastUpdatedBy, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
    }

    /**
     * Constructs a Customer object with essential details.
     *
     * @param customerId    The unique ID of the customer.
     * @param customerName  The name of the customer.
     * @param address       The address of the customer.
     * @param postalCode    The postal code of the customer's address.
     * @param phoneNumber     The phone number of the customer.
     * @param divisionId      The ID of the associated division.
     */
    public Customer(int customerId, String customerName, String address, String postalCode, String phoneNumber, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.divisionId = divisionId;
    }

    /**
     * Gets the customer ID.
     *
     * @return The customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID.
     *
     * @param customerId The customer ID to set.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the customer name.
     *
     * @return The customer name.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the customer name.
     *
     * @param customerName The customer name to set.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the customer's address.
     *
     * @return The customer's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the customer's address.
     *
     * @param address The address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the customer's postal code.
     *
     * @return The customer's postal code.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the customer's postal code.
     *
     * @param postalCode The postal code to set.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the customer's phone number.
     *
     * @return The customer's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the customer's phone number.
     *
     * @param phoneNumber The phone number to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the date and time the customer record was created.
     *
     * @return The creation date and time.
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the date and time the customer record was created.
     *
     * @param createdDate The creation date and time to set.
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the user who created the customer record.
     *
     * @return The user who created the record.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user who created the customer record.
     *
     * @param createdBy The user who created the record to set.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the date and time the customer record was last updated.
     *
     * @return The last updated date and time.
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdated;
    }

    /**
     * Sets the date and time the customer record was last updated.
     *
     * @param lastUpdated The last updated date and time to set.
     */
    public void setLastUpdate(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * Gets the user who last updated the customer record.
     *
     * @return The user who last updated the record.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the user who last updated the customer record.
     *
     * @param lastUpdatedBy The user who last updated the record to set.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
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
}

