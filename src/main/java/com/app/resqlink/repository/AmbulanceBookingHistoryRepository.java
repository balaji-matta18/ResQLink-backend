package com.app.resqlink.repository;

import com.app.resqlink.model.AmbulanceBookingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AmbulanceBookingHistoryRepository extends JpaRepository<AmbulanceBookingHistory, UUID> {
    List<AmbulanceBookingHistory> findByUserUserId(UUID userId);

    // List<AmbulanceBookingHistory> findByDriverDriverId(UUID driverId);
}
