package com.app.resqlink.controller;

import com.app.resqlink.model.DishaProfile;
import com.app.resqlink.service.DishaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/disha")
@CrossOrigin
public class DishaController {

    @Autowired
    private DishaService dishaService;

    // Create a new Disha profile
    @PostMapping("/create/{userId}")
    public DishaProfile createProfile(@PathVariable UUID userId, @Valid @RequestBody DishaProfile profile) {
        return dishaService.createProfile(userId, profile);
    }

    // Get Disha profile by user ID
    @GetMapping("/{userId}")
    public DishaProfile getProfile(@PathVariable UUID userId) {
        return dishaService.getProfileById(userId);
    }

    // Update Disha profile
    @PostMapping("/update/{userId}")
    public DishaProfile updateProfile(@PathVariable UUID userId, @Valid @RequestBody DishaProfile profile) {
        return dishaService.updateProfile(userId, profile);
    }

    // Delete Disha profile
    @DeleteMapping("/{userId}")
    public void deleteProfile(@PathVariable UUID userId) {
        dishaService.deleteProfile(userId);
    }

    // Get all Disha profiles
    @GetMapping("/all")
    public List<DishaProfile> getAllProfiles() {
        return dishaService.getAllProfiles();
    }

    @PostMapping("/sos/increment/{userId}")
    public DishaProfile incrementSosCount(@PathVariable UUID userId) {
        return dishaService.incrementSosCount(userId);
    }
}
