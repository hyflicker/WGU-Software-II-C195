package arobertson.C195.Models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * User Class - Creates user object and adds getters and setters.
 */
public class User {
    private int userId;
    private String username;
    private String password;
    private LocalDateTime createdDate;
    private String createdByDate;
    private Timestamp lastUpdated;
    private String lastUpdatedBy;

    /**
     * Constructs a User object with full details.
     *
     * @param userId        The unique ID of the user.
     * @param username      The username.
     * @param password      The password.
     * @param createdDate     The date and time the user record was created.
     * @param createdByDate   The user who created the user record.
     * @param lastUpdated     The date and time the user record was last updated.
     * @param lastUpdatedBy   The user who last updated the user record.
     */
    public User(int userId, String username, String password, LocalDateTime createdDate, String createdByDate, Timestamp lastUpdated, String lastUpdatedBy) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.createdDate = createdDate;
        this.createdByDate = createdByDate;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Constructs a User object with ID and username.
     *
     * @param userId   The unique ID of the user.
     * @param username The username.
     */
    public User(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    /**
     * Constructs a User object with username.
     *
     * @param username The username.
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * Gets the user ID.
     *
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the username.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the creation date and time.
     *
     * @return The creation date and time.
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the creation date and time.
     *
     * @param createdDate The creation date and time to set.
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the creator of the user record.
     *
     * @return The creator of the user record.
     */
    public String getCreatedByDate() {
        return createdByDate;
    }

    /**
     * Sets the creator of the user record.
     *
     * @param createdByDate The creator of the user record.
     */
    public void setCreatedByDate(String createdByDate) {
        this.createdByDate = createdByDate;
    }

    /**
     * Gets the last updated timestamp.
     *
     * @return The last updated timestamp.
     */
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets the last updated timestamp.
     *
     * @param lastUpdated The last updated timestamp to set.
     */
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * Gets the last updater of the user record.
     *
     * @return The last updater of the user record.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the last updater of the user record.
     *
     * @param lastUpdatedBy The last updater of the user record to set.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}

