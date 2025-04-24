package arobertson.C195.Models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class User {
    private int userId;
    private String username;
    private String password;
    private LocalDateTime createdDate;
    private String createdByDate;
    private Timestamp lastUpdated;
    private String lastUpdatedBy;

    public User (int userId, String username, String password, LocalDateTime createdDate, String createdByDate, Timestamp lastUpdated, String lastUpdatedBy){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.createdDate = createdDate;
        this.createdByDate = createdByDate;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public User(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public User(String username){
        this.username=username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedByDate() {
        return createdByDate;
    }

    public void setCreatedByDate(String createdByDate) {
        this.createdByDate = createdByDate;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
