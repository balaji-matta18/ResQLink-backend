package com.app.resqlink.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "BloodDonationHistory")
public class BloodDonationHistory {

    @Id
    @GeneratedValue
    private UUID donationId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private BloodDonationProfile bloodDonationProfile;

    @Column(nullable = false)
    private LocalDate donationDate;

    @Column(nullable = false)
    private UUID receiverId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private Map<String, Double> location;

    @Column(nullable = false)
    private Integer numberOfUnits = 1;

    @Column(nullable = false)
    private Integer rewardPointsEarned = 0;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // Getters and Setters

    public UUID getDonationId() {
        return donationId;
    }

    public void setDonationId(UUID donationId) {
        this.donationId = donationId;
    }

    public BloodDonationProfile getBloodDonationProfile() {
        return bloodDonationProfile;
    }

    public void setBloodDonationProfile(BloodDonationProfile bloodDonationProfile) {
        this.bloodDonationProfile = bloodDonationProfile;
    }

    public LocalDate getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }

    public UUID getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(UUID receiverId) {
        this.receiverId = receiverId;
    }

    public Map<String, Double> getLocation() {
        return location;
    }

    public void setLocation(Map<String, Double> location) {
        this.location = location;
    }

    public Integer getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(Integer numberOfUnits) {
        if (numberOfUnits != null && numberOfUnits > 0) {
            this.numberOfUnits = numberOfUnits;
            this.rewardPointsEarned = numberOfUnits * 10; // Reward logic: 10 points per unit
        } else {
            throw new IllegalArgumentException("Number of units must be greater than 0");
        }
    }

    public Integer getRewardPointsEarned() {
        return rewardPointsEarned;
    }

    public void setRewardPointsEarned(Integer rewardPointsEarned) {
        this.rewardPointsEarned = rewardPointsEarned;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
