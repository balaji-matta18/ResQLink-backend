package com.app.resqlink.service;

import com.app.resqlink.model.User;
import com.app.resqlink.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Register user with either email or phone
     */
   public User updateBasicDetails(UUID userId, User user) {
    User existingUser = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    // Check if the new email is used by another user
    if (user.getEmail() != null && !user.getEmail().equals(existingUser.getEmail())) {
        Optional<User> userWithSameEmail = userRepository.findByEmail(user.getEmail());
        if (userWithSameEmail.isPresent() && !userWithSameEmail.get().getUserId().equals(userId)) {
            throw new RuntimeException("Email already exists");
        }
        existingUser.setEmail(user.getEmail());
    }

    // Check if the new phone number is used by another user
    if (user.getPhoneNumber() != null && !user.getPhoneNumber().equals(existingUser.getPhoneNumber())) {
        Optional<User> userWithSamePhone = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if (userWithSamePhone.isPresent() && !userWithSamePhone.get().getUserId().equals(userId)) {
            throw new RuntimeException("Phone number already exists");
        }
        existingUser.setPhoneNumber(user.getPhoneNumber());
    }

    if (user.getAge() != null) {
        existingUser.setAge(user.getAge());
    }

    if (user.getDateOfBirth() != null) {
        existingUser.setDateOfBirth(user.getDateOfBirth());
    }

    if (user.getGender() != null) {
        existingUser.setGender(user.getGender());
    }

    if (user.getHomeAddress() != null) {
        existingUser.setHomeAddress(user.getHomeAddress());
    }

    if (user.getGpsLocation() != null && !user.getGpsLocation().isEmpty()) {
        existingUser.setGpsLocation(user.getGpsLocation());
    }

   existingUser.setUpdatedAt(java.sql.Timestamp.valueOf(LocalDateTime.now()));

    return userRepository.save(existingUser);
}

    /**
     * Login user with email or phone number + password
     */
    public User loginUser(String identifier, String password) {
        if (identifier == null || identifier.trim().isEmpty()) {
            throw new RuntimeException("Email or phone number is required");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new RuntimeException("Password is required");
        }

        Optional<User> userOptional = userRepository.findByEmail(identifier);

        if (userOptional.isEmpty()) {
            userOptional = userRepository.findByPhoneNumber(identifier);
        }

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid email/phone or password");
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    /**
     * Create user (legacy method - consider using registerUser instead)
     */
    public User createUser(User user) {
        if ((user.getEmail() == null || user.getEmail().trim().isEmpty())
                && (user.getPhoneNumber() == null || user.getPhoneNumber().trim().isEmpty())) {
            throw new RuntimeException("Either email or phone number is required for registration");
        }

        if (user.getPasswordHash() == null || user.getPasswordHash().trim().isEmpty()) {
            throw new RuntimeException("Password is required");
        }

        if (user.getEmail() != null && userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (user.getPhoneNumber() != null && userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new RuntimeException("Phone number already exists");
        }

        // Hash password
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

    public User updateUser(UUID id, User updatedUser) {
        User existing = getUserById(id);

        // Update only non-null fields
        if (updatedUser.getFirstName() != null) {
            existing.setFirstName(updatedUser.getFirstName());
        }

        if (updatedUser.getLastName() != null) {
            existing.setLastName(updatedUser.getLastName());
        }

        if (updatedUser.getEmail() != null) {
            // Check if new email already exists for another user
            if (!existing.getEmail().equals(updatedUser.getEmail())
                    && userRepository.existsByEmail(updatedUser.getEmail())) {
                throw new RuntimeException("Email already exists");
            }
            existing.setEmail(updatedUser.getEmail());
        }

        if (updatedUser.getPhoneNumber() != null) {
            // Check if new phone number already exists for another user
            if ((existing.getPhoneNumber() == null || !existing.getPhoneNumber().equals(updatedUser.getPhoneNumber()))
                    && userRepository.existsByPhoneNumber(updatedUser.getPhoneNumber())) {
                throw new RuntimeException("Phone number already exists");
            }
            existing.setPhoneNumber(updatedUser.getPhoneNumber());
        }

        if (updatedUser.getGender() != null) {
            existing.setGender(updatedUser.getGender());
        }

        if (updatedUser.getAge() != null) {
            existing.setAge(updatedUser.getAge());
        }

        if (updatedUser.getDateOfBirth() != null) {
            existing.setDateOfBirth(updatedUser.getDateOfBirth());
        }

        if (updatedUser.getProfilePhotoUrl() != null) {
            existing.setProfilePhotoUrl(updatedUser.getProfilePhotoUrl());
        }

        if (updatedUser.getHomeAddress() != null) {
            existing.setHomeAddress(updatedUser.getHomeAddress());
        }

        if (updatedUser.getGpsLocation() != null) {
            existing.setGpsLocation(updatedUser.getGpsLocation());
        }

        return userRepository.save(existing);
    }

    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    /**
     * Change user password
     */
    public void changePassword(UUID userId, String currentPassword, String newPassword) {
        User user = getUserById(userId);

        if (!passwordEncoder.matches(currentPassword, user.getPasswordHash())) {
            throw new RuntimeException("Current password is incorrect");
        }

        user.setPasswordHash(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    /**
     * Check if email exists
     */
    public Optional<User> emailExists(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Check if phone number exists
     */
    public boolean phoneNumberExists(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }
}