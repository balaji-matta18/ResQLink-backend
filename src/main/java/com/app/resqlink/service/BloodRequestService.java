package com.app.resqlink.service;

import com.app.resqlink.model.BloodRequest;
import com.app.resqlink.repository.BloodDonationProfileRepository;
import com.app.resqlink.repository.BloodRequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BloodRequestService {

    private final BloodRequestRepository bloodRequestRepository;

    private final BloodDonationProfileRepository bloodDonationProfileRepository;

    public BloodRequestService(BloodRequestRepository bloodRequestRepository,
            BloodDonationProfileRepository bloodDonationProfileRepository) {
        this.bloodRequestRepository = bloodRequestRepository;
        this.bloodDonationProfileRepository = bloodDonationProfileRepository;
    }

    public BloodRequest createRequest(BloodRequest bloodRequest) {
        return bloodRequestRepository.save(bloodRequest);
    }

    // not needed
    public BloodRequest updateRequest(UUID requestId, BloodRequest updatedRequest) {
        BloodRequest existing = bloodRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found with ID: " + requestId));

        existing.setStatus(updatedRequest.getStatus()); // Ensure valid enum
        return bloodRequestRepository.save(existing);
    }

    public void deleteRequest(UUID requestId) {
        bloodRequestRepository.deleteById(requestId);
    }

    public BloodRequest getRequestById(UUID requestId) {
        return bloodRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found with ID: " + requestId));
    }

    public List<BloodRequest> getAllRequests() {
        return bloodRequestRepository.findAll();
    }

    public List<BloodRequest> getRequestsByUserId(UUID userId) {
        return bloodRequestRepository.findByBloodDonationProfile_UserId(userId);
    }

    public BloodRequest addAcceptedUser(UUID requestId, UUID userId) {
        // Check if user exists in BloodDonationProfiles
        if (!bloodDonationProfileRepository.existsById(userId)) {
            throw new IllegalArgumentException("User does not exist in BloodDonationProfiles.");
        }

        // Fetch the blood request
        BloodRequest request = bloodRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("BloodRequest not found."));
        // Add to acceptedUserIds if not already present
        List<UUID> acceptedUserIds = request.getAcceptedUserIds();
        if (acceptedUserIds == null) {
            acceptedUserIds = new ArrayList<>();
        }

        if (!acceptedUserIds.contains(userId)) {
            acceptedUserIds.add(userId);
            request.setAcceptedUserIds(acceptedUserIds);
            request.setStatus(BloodRequest.RequestStatus.PROGRESS);
            bloodRequestRepository.save(request);
        }

        return request;
    }
}
