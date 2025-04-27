package arobertson.C195.Models;

import java.time.LocalDateTime;

/**
 * Appointment Class - Multiple object configurations with getters and setters.
 */
public class Appointment {
    private int count;
    private int appointmentId;
    private int customerId;
    private int userId;
    private int contactId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor to create appointment object with these params.
     * @param appointmentId ID of appointment
     * @param customerId ID of customer
     * @param userId ID of user
     * @param contactId ID of contact
     * @param title Title of appointment
     * @param description Description of appointment
     * @param location Location of appointment
     * @param type Type of appointment
     * @param start Start date and time of appointment
     * @param end End date and time of appointment
     */
    public Appointment(int appointmentId, int customerId, int userId, int contactId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor to create appointment object with these params.
     * @param appointmentId ID for appointment
     * @param title Title for appointment
     * @param type Type of appointment
     * @param description Description of appointment
     * @param start Start date and time of appointment
     * @param end End date and time of appointment
     * @param customerId ID of customer.
     */
    public Appointment(int appointmentId, String title, String type, String description, LocalDateTime start, LocalDateTime end, int customerId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
    }

    /**
     * Constructor to create appointment object with these params.
     * @param type Type of appointment
     * @param count Count of appointments with the same type.
     */
    public Appointment(String type,int count){
        this.type = type;
        this.count = count;
    }

    /**
     * Getter for count.
     * @return Count
     */
    public int getCount() {
        return count;
    }

    /**
     * Setter for count
     * @param count count of appointments
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Getter for appointment ID
     * @return appointment ID
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Setter for appointment ID
     * @param appointmentId ID to set to appointment
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Getter for customer ID
     * @return Customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Setter for customer ID
     * @param customerId Customer ID that is to be set to appointment
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Getter for User ID
     * @return User ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Setter for User ID
     * @param userId User ID to be set to appointment
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Getter for Contact ID
     * @return Contact ID
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Setter for contact ID.
     * @param contactId contact ID that is to be set to appointment
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Getter for title
     * @return Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title
     * @param title Title to be set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for description
     * @return Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description
     * @param description Description to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for location
     * @return Location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter for location
     * @param location Location to be set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Getter for type
     * @return Type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for type
     * @param type Type to be set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for start date and time
     * @return Start date and time
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Setter for start date and time
     * @param start Start date and time to be set
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Getter for end date and time
     * @return End date and time
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Setter for end date and time
     * @param end End date and time
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
