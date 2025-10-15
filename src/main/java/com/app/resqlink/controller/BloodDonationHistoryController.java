package com.app.resqlink.controller;

import com.app.resqlink.model.BloodDonationHistory;
import com.app.resqlink.service.BloodDonationHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/donations-history")
public class BloodDonationHistoryController {

    private final BloodDonationHistoryService donationService;

    public BloodDonationHistoryController(BloodDonationHistoryService donationService) {
        this.donationService = donationService;
    }

    @PostMapping("/create/{userId}")
    public BloodDonationHistory createDonation(@PathVariable UUID userId, @RequestBody BloodDonationHistory donation) {
        return donationService.createDonation(userId, donation);
    }

    @GetMapping
    public List<BloodDonationHistory> getAllDonations() {
        return donationService.getAllDonations();
    }

    @GetMapping("/{donationId}")
    public BloodDonationHistory getDonationById(@PathVariable UUID donationId) {
        return donationService.getDonationById(donationId);
    }

    @GetMapping("/user/{userId}")
    public List<BloodDonationHistory> getDonationsByUser(@PathVariable UUID userId) {
        return donationService.getDonationsByUserId(userId);
    }

    @PutMapping("/update/{donationId}")
    public BloodDonationHistory updateDonation(@PathVariable UUID donationId,
            @RequestBody BloodDonationHistory updated) {
        return donationService.updateDonation(donationId, updated);
    }

    @DeleteMapping("/delete/{donationId}")
    public String deleteDonation(@PathVariable UUID donationId) {
        donationService.deleteDonation(donationId);
        return "Donation with ID " + donationId + " deleted successfully.";
    }
}
