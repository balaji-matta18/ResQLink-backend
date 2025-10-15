package com.app.resqlink.repository;

import com.app.resqlink.model.AmbulanceLiveLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AmbulanceLiveLocationRepository extends JpaRepository<AmbulanceLiveLocation, UUID> {
    AmbulanceLiveLocation findByBookingBookingId(UUID bookingId);
}
