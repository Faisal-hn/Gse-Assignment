package com.gse.assignment.entry;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gse.assignment.utils.ContactEntryDeserializer;

@JsonDeserialize(using = ContactEntryDeserializer.class)
public class ContactEntry {
    private String email;
    private Long phone;
    private Long primaryId;

    public ContactEntry() {
    }

    public ContactEntry(String email, Long phone, Long primaryId) {
        this.email = email;
        this.phone = phone;
        this.primaryId = primaryId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getPrimaryId() {
        return primaryId;
    }
}
