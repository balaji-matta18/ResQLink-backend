package com.app.resqlink.model;

import jakarta.persistence.*;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "DishaProfiles")

public class DishaProfile {

    @Id
    private UUID userId;

    // List of maps with "name" and "phone" keys, stored as JSON
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<Map<String, String>> emergencyContacts;

    private String suspectImageUrl;

    private Integer sosCount = 0;

    // Getters and Setters
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<Map<String, String>> getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(List<Map<String, String>> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    public String getSuspectImageUrl() {
        return suspectImageUrl;
    }

    public void setSuspectImageUrl(String suspectImageUrl) {
        this.suspectImageUrl = suspectImageUrl;
    }

    public Integer getSosCount() {
        return sosCount;
    }

    public void setSosCount(Integer sosCount) {
        this.sosCount = sosCount;
    }
}
