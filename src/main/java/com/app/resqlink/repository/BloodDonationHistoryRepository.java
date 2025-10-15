package com.app.resqlink.repository;

import com.app.resqlink.model.BloodDonationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BloodDonationHistoryRepository extends JpaRepository<BloodDonationHistory, UUID> {
    List<BloodDonationHistory> findByBloodDonationProfileUserId(UUID userId);
}
