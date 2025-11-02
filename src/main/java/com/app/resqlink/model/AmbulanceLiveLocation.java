package com.app.resqlink.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "AmbulanceLiveLocation")
public class AmbulanceLiveLocation {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "bookingId", nullable = false)
    private AmbulanceBookingHistory booking;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private Map<String, Double> currentLocation;

    @CreationTimestamp
    private LocalDateTime lastUpdated;

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AmbulanceBookingHistory getBooking() {
        return booking;
    }

    public void setBooking(AmbulanceBookingHistory booking) {
        this.booking = booking;
    }

    public Map<String, Double> getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Map<String, Double> currentLocation) {
        this.currentLocation = currentLocation;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
