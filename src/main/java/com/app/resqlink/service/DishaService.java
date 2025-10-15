package com.app.resqlink.service;

import com.app.resqlink.model.DishaProfile;
import com.app.resqlink.repository.DishaRepository;
import com.app.resqlink.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DishaService {

    private final DishaRepository dishaRepository;

    @Autowired
    private UserRepository userRepository;

    public DishaService(DishaRepository dishaRepository) {
        this.dishaRepository = dishaRepository;
    }

    public DishaProfile createProfile(UUID userId, DishaProfile profile) {
        if (dishaRepository.existsById(userId)) {
            throw new IllegalStateException("Disha profile already exists for user ID: " + userId);
        }
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        DishaProfile newProfile = new DishaProfile();
        newProfile.setUserId(userId);
        if (profile.getEmergencyContacts() != null)
            newProfile.setEmergencyContacts(profile.getEmergencyContacts());
        return dishaRepository.save(newProfile);
    }

    public DishaProfile getProfileById(UUID userId) {
        return dishaRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Disha profile not found for user ID: " + userId));
    }

    public List<DishaProfile> getAllProfiles() {
        return dishaRepository.findAll();
    }

    public DishaProfile updateProfile(UUID userId, DishaProfile updatedProfile) {
        DishaProfile existing = getProfileById(userId);

        if (updatedProfile.getEmergencyContacts() != null)
            existing.setEmergencyContacts(updatedProfile.getEmergencyContacts());

        return dishaRepository.save(existing);
    }

    public DishaProfile incrementSosCount(UUID userId) {
        DishaProfile profile = getProfileById(userId);
        profile.setSosCount(profile.getSosCount() + 1);
        return dishaRepository.save(profile);
    }

    public void deleteProfile(UUID userId) {
        if (!dishaRepository.existsById(userId)) {
            throw new EntityNotFoundException("Disha profile not found for user ID: " + userId);
        }
        dishaRepository.deleteById(userId);
    }
}
