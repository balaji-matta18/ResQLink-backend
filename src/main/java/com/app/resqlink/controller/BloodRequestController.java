package com.app.resqlink.controller;

import com.app.resqlink.model.BloodRequest;
import com.app.resqlink.service.BloodRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/blood-requests")
@CrossOrigin
public class BloodRequestController {

    @Autowired
    private BloodRequestService bloodRequestService;

    // Create new blood request
    @PostMapping("/create")
    public BloodRequest createRequest(@Valid @RequestBody BloodRequest bloodRequest) {
        return bloodRequestService.createRequest(bloodRequest);
    }

    // Get a blood request by ID
    @GetMapping("/{id}")
    public BloodRequest getRequestById(@PathVariable UUID id) {
        return bloodRequestService.getRequestById(id);
    }

    // Get all blood requests
    @GetMapping
    public List<BloodRequest> getAllRequests() {
        return bloodRequestService.getAllRequests();
    }

    // Get requests by user ID
    @GetMapping("/user/{userId}")
    public List<BloodRequest> getRequestsByUserId(@PathVariable UUID userId) {
        return bloodRequestService.getRequestsByUserId(userId);
    }

    // Update an existing blood request
    @PostMapping("/update/{id}")
    public BloodRequest updateRequest(@PathVariable UUID id, @Valid @RequestBody BloodRequest updatedRequest) {
        return bloodRequestService.updateRequest(id, updatedRequest);
    }

    @PostMapping("/accept-user/{requestId}")
    public BloodRequest addAcceptedUser(
            @PathVariable UUID requestId,
            @RequestParam UUID userId) {
        return bloodRequestService.addAcceptedUser(requestId, userId);
    }

    // Delete a request by ID
    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable UUID id) {
        bloodRequestService.deleteRequest(id);
    }
}
