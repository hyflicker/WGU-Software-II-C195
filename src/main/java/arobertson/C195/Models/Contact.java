package arobertson.C195.Models;

/**
 * Contact Class - Creates customer object and adds getters and setters.
 */
public class Contact {
    private int contactId;
    private String contactName;
    private String email;

    /**
     * Constructor for Contact class.
     * @param contactId ID of the contact.
     * @param contactName Name of the contact.
     * @param email Email address of the contact.
     */
    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Gets the contact ID.
     * @return The contact ID.
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets the contact ID.
     * @param contactId The contact ID to set.
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Gets the contact name.
     * @return The contact name.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the contact name.
     * @param contactName The contact name to set.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Gets the contact's email address.
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the contact's email address.
     * @param email The email address to set.
     */
    public void dsetEmail(String email) {
        this.email = email;
    }
}
