package com.app.resqlink.service;

import com.app.resqlink.model.BloodDonationProfile;
import com.app.resqlink.repository.BloodDonationProfileRepository;
import com.app.resqlink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class BloodDonationProfileService {

    @Autowired
    private BloodDonationProfileRepository bloodDonationProfileRepository;
    @Autowired
    private UserRepository userRepository;

    public BloodDonationProfile createProfile(UUID userId, BloodDonationProfile profileData) {

        if (bloodDonationProfileRepository.existsById(userId)) {
            throw new IllegalStateException("Profile already exists for user");
        }

        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        BloodDonationProfile profile = new BloodDonationProfile();
        profile.setUserId(userId);

        if (profileData.getBloodGroup() != null) {
            profile.setBloodGroup(profileData.getBloodGroup());
        }

        if (profileData.getLastDonationDate() != null) {
            profile.setLastDonationDate(profileData.getLastDonationDate());
        }

        return bloodDonationProfileRepository.save(profile);
    }

    // Get all profiles
    public List<BloodDonationProfile> getAllProfiles() {
        return bloodDonationProfileRepository.findAll();
    }

    // Get profile by user ID
    public Optional<BloodDonationProfile> getProfileByUserId(UUID userId) {
        return bloodDonationProfileRepository.findById(userId);
    }

    // Update existing profile
    public BloodDonationProfile updateProfile(UUID userId, BloodDonationProfile updatedProfile) {
        BloodDonationProfile existing = bloodDonationProfileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Blood donation profile not found for user: " + userId));

        if (updatedProfile.getBloodGroup() != null) {
            existing.setBloodGroup(updatedProfile.getBloodGroup());
        }

        if (updatedProfile.getLastDonationDate() != null) {
            existing.setLastDonationDate(updatedProfile.getLastDonationDate());
        }

        if (updatedProfile.getDonationFrequency() != null) {
            existing.setDonationFrequency(updatedProfile.getDonationFrequency());
        }

        if (updatedProfile.getPreferredRadiusKm() != null) {
            existing.setPreferredRadiusKm(updatedProfile.getPreferredRadiusKm());
        }

        return bloodDonationProfileRepository.save(existing);
    }

    // Delete profile by user ID
    public void deleteProfile(UUID userId) {
        if (!bloodDonationProfileRepository.existsByUserId(userId)) {
            throw new RuntimeException("Blood donation profile not found for user: " + userId);
        }
        bloodDonationProfileRepository.deleteById(userId);
    }

    // Additional business methods
    public List<BloodDonationProfile> getProfilesByBloodGroup(String bloodGroup) {
        return bloodDonationProfileRepository.findByBloodGroup(bloodGroup);
    }

    public List<BloodDonationProfile> getEligibleDonors() {
        return bloodDonationProfileRepository.findByEligibilityStatusTrue();
    }

    public List<BloodDonationProfile> getEligibleDonorsByBloodGroup(String bloodGroup) {
        return bloodDonationProfileRepository.findByBloodGroupAndEligibilityStatusTrue(bloodGroup);
    }

    public List<BloodDonationProfile> getDonorsWithinRadius(Integer radius) {
        return bloodDonationProfileRepository.findDonorsWithinRadius(radius);
    }

    public List<BloodDonationProfile> getTopDonors() {
        return bloodDonationProfileRepository.findTopDonors();
    }

    // Check if profile exists
    public boolean profileExists(UUID userId) {
        return bloodDonationProfileRepository.existsByUserId(userId);
    }
}