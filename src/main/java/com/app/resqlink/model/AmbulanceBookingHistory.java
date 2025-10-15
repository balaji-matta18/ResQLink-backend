package com.app.resqlink.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "AmbulanceBookingHistory")
public class AmbulanceBookingHistory {

    @Id
    @GeneratedValue
    private UUID bookingId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private UUID driverId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Double> pickupLocation;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Double> dropLocation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status = BookingStatus.PENDING;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public enum BookingStatus {
        PENDING,
        PROGRESS,
        COMPLETED,
        CANCELLED
    }

    // Getters and Setters

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getDriverId() {
        return driverId;
    }   
public void setDriverId(UUID driverId) {
        this.driverId = driverId;
    }
    public Map<String, Double> getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Map<String, Double> pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Map<String, Double> getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(Map<String, Double> dropLocation) {
        this.dropLocation = dropLocation;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
