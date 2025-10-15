package com.app.resqlink.controller;

import com.app.resqlink.model.BloodDonationProfile;
import com.app.resqlink.service.BloodDonationProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/blood-donation")
@CrossOrigin(origins = "*")
public class BloodDonationProfileController {

    @Autowired
    private BloodDonationProfileService bloodDonationProfileService;

    // Create or update basic blood donation profile
    @PostMapping("/create/{userId}")
    public BloodDonationProfile createProfile(@PathVariable UUID userId,
            @Valid @RequestBody BloodDonationProfile profile) {
        return bloodDonationProfileService.createProfile(userId, profile);
    }

    // Get profile by user ID
    @GetMapping("/{userId}")
    public BloodDonationProfile getProfile(@PathVariable UUID userId) {
        return bloodDonationProfileService.getProfileByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for userId: " + userId));
    }

    // Update profile
    @PostMapping("/update/{userId}")
    public BloodDonationProfile updateProfile(@PathVariable UUID userId,
            @Valid @RequestBody BloodDonationProfile profile) {
        return bloodDonationProfileService.updateProfile(userId, profile);
    }

    // Delete profile
    @DeleteMapping("/{userId}")
    public void deleteProfile(@PathVariable UUID userId) {
        bloodDonationProfileService.deleteProfile(userId);
    }

    // Check if profile exists
    @GetMapping("/exists/{userId}")
    public boolean profileExists(@PathVariable UUID userId) {
        return bloodDonationProfileService.profileExists(userId);
    }

    // Get all profiles
    @GetMapping("/all")
    public List<BloodDonationProfile> getAllProfiles() {
    return bloodDonationProfileService.getAllProfiles();
    }

    // Get profiles by blood group
    @GetMapping("/blood-group")
    public List<BloodDonationProfile> getByBloodGroup(@RequestParam String bloodGroup) {
        return bloodDonationProfileService.getProfilesByBloodGroup(bloodGroup);
    }

    // Get eligible donors
    @GetMapping("/eligible")
    public List<BloodDonationProfile> getEligibleDonors() {
        return bloodDonationProfileService.getEligibleDonors();
    }

    // Get eligible donors by blood group
    @GetMapping("/eligible/blood-group")
    public List<BloodDonationProfile> getEligibleByGroup(@RequestParam String bloodGroup) {
        return bloodDonationProfileService.getEligibleDonorsByBloodGroup(bloodGroup);
    }

    // Get donors within radius
    @GetMapping("/within-radius")
    public List<BloodDonationProfile> getWithinRadius(@RequestParam int radius) {
        return bloodDonationProfileService.getDonorsWithinRadius(radius);
    }

    // Get top donors
    @GetMapping("/top")
    public List<BloodDonationProfile> getTopDonors() {
        return bloodDonationProfileService.getTopDonors();
    }
}
