package com.app.resqlink.repository;

import com.app.resqlink.model.BloodDonationProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface BloodDonationProfileRepository extends JpaRepository<BloodDonationProfile, UUID> {

    // Find by blood group
    List<BloodDonationProfile> findByBloodGroup(String bloodGroup);

    // Find eligible donors
    List<BloodDonationProfile> findByEligibilityStatusTrue();

    // Find by blood group and eligibility
    List<BloodDonationProfile> findByBloodGroupAndEligibilityStatusTrue(String bloodGroup);

    // Find by donation frequency
    List<BloodDonationProfile> findByDonationFrequency(String donationFrequency);

    // Find donors within radius (custom query example)
    @Query("SELECT bdp FROM BloodDonationProfile bdp WHERE bdp.preferredRadiusKm <= :radius")
    List<BloodDonationProfile> findDonorsWithinRadius(@Param("radius") Integer radius);

    // Find top donors by total donations
    @Query("SELECT bdp FROM BloodDonationProfile bdp ORDER BY bdp.totalDonations DESC")
    List<BloodDonationProfile> findTopDonors();

    // Check if profile exists for user
    boolean existsByUserId(UUID userId);
}