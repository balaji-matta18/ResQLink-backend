package com.app.resqlink.service;

import com.app.resqlink.model.BloodDonationHistory;
import com.app.resqlink.model.BloodDonationProfile;
import com.app.resqlink.repository.BloodDonationHistoryRepository;
import com.app.resqlink.repository.BloodDonationProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BloodDonationHistoryService {

    private final BloodDonationHistoryRepository donationHistoryRepository;
    private final BloodDonationProfileRepository profileRepository;

    public BloodDonationHistoryService(BloodDonationHistoryRepository donationHistoryRepository,
            BloodDonationProfileRepository profileRepository) {
        this.donationHistoryRepository = donationHistoryRepository;
        this.profileRepository = profileRepository;
    }

    public BloodDonationHistory createDonation(UUID userId, BloodDonationHistory history) {

        BloodDonationProfile profile = profileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("BloodDonationProfile not found for user ID: " + userId));

        if (history.getDonationDate() == null) {
            throw new IllegalArgumentException("Donation date is required.");
        }
        if (history.getReceiverId() == null) {
            throw new IllegalArgumentException("Receiver ID is required.");
        }
        if (history.getLocation() == null || history.getLocation().isEmpty()) {
            throw new IllegalArgumentException("Location (latitude and longitude) is required.");
        }
        if (history.getNumberOfUnits() == null || history.getNumberOfUnits() <= 0) {
            throw new IllegalArgumentException("Number of units must be greater than 0.");
        }

        boolean receiverExists = profileRepository.existsById(history.getReceiverId());
        if (!receiverExists) {
            throw new IllegalArgumentException("Receiver ID does not exist in BloodDonationProfile.");
        }

        history.setBloodDonationProfile(profile);

        // Reward points are auto-calculated in the setter of numberOfUnits

        return donationHistoryRepository.save(history);
    }

    public List<BloodDonationHistory> getAllDonations() {
        return donationHistoryRepository.findAll();
    }

    public List<BloodDonationHistory> getDonationsByUserId(UUID userId) {
        return donationHistoryRepository.findByBloodDonationProfileUserId(userId);
    }

    public BloodDonationHistory getDonationById(UUID donationId) {
        return donationHistoryRepository.findById(donationId)
                .orElseThrow(() -> new RuntimeException("Donation not found with ID: " + donationId));
    }

    public BloodDonationHistory updateDonation(UUID donationId, BloodDonationHistory updated) {
        BloodDonationHistory existing = getDonationById(donationId);

        existing.setDonationDate(updated.getDonationDate());
        existing.setReceiverId(updated.getReceiverId());
        existing.setLocation(updated.getLocation());
        existing.setNumberOfUnits(updated.getNumberOfUnits());

        return donationHistoryRepository.save(existing);
    }

    public void deleteDonation(UUID donationId) {
        if (!donationHistoryRepository.existsById(donationId)) {
            throw new RuntimeException("Donation not found with ID: " + donationId);
        }
        donationHistoryRepository.deleteById(donationId);
    }
}
