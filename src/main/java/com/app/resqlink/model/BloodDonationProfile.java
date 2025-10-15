// BloodDonationProfile Entity
package com.app.resqlink.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table(name = "BloodDonationProfiles")
public class BloodDonationProfile {

    @Id
    private UUID userId;

    @Column(name = "bloodGroup", length = 5)
    @Pattern(regexp = "^(A|B|AB|O)[+-]$", message = "Invalid blood group format")
    private String bloodGroup;

    @Column(name = "lastDonationDate")
    private LocalDate lastDonationDate;

    @Column(name = "donationFrequency", length = 50)
    @Pattern(regexp = "^(occasionally|every 3 months|every 6 months|every 1 year)$", message = "Invalid donation frequency")
    private String donationFrequency;

    @Column(name = "eligibilityStatus")
    private Boolean eligibilityStatus;

    @Column(name = "preferredRadiusKm")
    @Min(value = 0, message = "Preferred radius must be non-negative")
    private Integer preferredRadiusKm;

    @Column(name = "totalDonations")
    @Min(value = 0, message = "Total donations must be non-negative")
    private Integer totalDonations;

    @Column(name = "totalRequestsRaised")
    @Min(value = 0, message = "Total requests raised must be non-negative")
    private Integer totalRequestsRaised;

    @Column(name = "campParticipationCount")
    @Min(value = 0, message = "Camp participation count must be non-negative")
    private Integer campParticipationCount;

    @Column(name = "rewardPoints")
    @Min(value = 0, message = "Reward points must be non-negative")
    private Integer rewardPoints;

    // Constructors
    public BloodDonationProfile() {
        this.lastDonationDate = LocalDate.of(2000, 1, 1);
        this.donationFrequency = "occasionally";
        this.eligibilityStatus = true;
        this.preferredRadiusKm = 5;
        this.totalDonations = 0;
        this.totalRequestsRaised = 0;
        this.campParticipationCount = 0;
        this.rewardPoints = 0;
    }

    // public BloodDonationProfile(User user) {
    // this();
    // this.user = user;
    // this.userId = user.getUserId();
    // }

    // Method to calculate eligibility based on last donation date
    @PrePersist
    @PreUpdate
    public void calculateEligibility() {
        if (lastDonationDate != null) {
            long daysSinceLastDonation = ChronoUnit.DAYS.between(lastDonationDate, LocalDate.now());
            this.eligibilityStatus = daysSinceLastDonation >= 90;
        }
    }

    // Getters and Setters
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    // public User getUser() {
    // return user;
    // }

    // public void setUser(User user) {
    // this.user = user;
    // if (user != null) {
    // this.userId = user.getUserId();
    // }
    // }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public LocalDate getLastDonationDate() {
        return lastDonationDate;
    }

    public void setLastDonationDate(LocalDate lastDonationDate) {
        this.lastDonationDate = lastDonationDate;
        calculateEligibility(); // Recalculate eligibility when donation date changes
    }

    public String getDonationFrequency() {
        return donationFrequency;
    }

    public void setDonationFrequency(String donationFrequency) {
        this.donationFrequency = donationFrequency;
    }

    public Boolean getEligibilityStatus() {
        return eligibilityStatus;
    }

    public void setEligibilityStatus(Boolean eligibilityStatus) {
        this.eligibilityStatus = eligibilityStatus;
    }

    public Integer getPreferredRadiusKm() {
        return preferredRadiusKm;
    }

    public void setPreferredRadiusKm(Integer preferredRadiusKm) {
        this.preferredRadiusKm = preferredRadiusKm;
    }

    public Integer getTotalDonations() {
        return totalDonations;
    }

    public void setTotalDonations(Integer totalDonations) {
        this.totalDonations = totalDonations;
    }

    public Integer getTotalRequestsRaised() {
        return totalRequestsRaised;
    }

    public void setTotalRequestsRaised(Integer totalRequestsRaised) {
        this.totalRequestsRaised = totalRequestsRaised;
    }

    public Integer getCampParticipationCount() {
        return campParticipationCount;
    }

    public void setCampParticipationCount(Integer campParticipationCount) {
        this.campParticipationCount = campParticipationCount;
    }

    public Integer getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(Integer rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
}