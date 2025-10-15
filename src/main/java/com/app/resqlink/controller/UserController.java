package com.app.resqlink.controller;

import com.app.resqlink.model.User;
import com.app.resqlink.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get user by ID
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }   
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    // Basic info for new user
    @PostMapping("/basic-info/{userId}")
    public User updateBasicUserInfo(@PathVariable UUID userId, @RequestBody User user) {
        return userService.updateBasicDetails(userId, user);
    }

    // Login user with email or phone + password
    @PostMapping("/login")
    public User loginUser(@RequestBody Map<String, String> loginRequest) {
        String identifier = loginRequest.get("identifier"); // could be email or phone
        String password = loginRequest.get("password");

        return userService.loginUser(identifier, password);
    }

    // create new user
    @PostMapping("/create")
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    // Update user by ID
    @PostMapping("/update/{userId}")
    public User updateUser(@PathVariable UUID userId, @Valid @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    // Change password (optional enhancement)
    @PostMapping("/change-password/{id}")
    public void changePassword(@PathVariable UUID id, @RequestBody Map<String, String> body) {
        String currentPassword = body.get("currentPassword");
        String newPassword = body.get("newPassword");

        userService.changePassword(id, currentPassword, newPassword);
    }

    // Check if email exists
    @GetMapping("/exists/email")
    public Optional<User> emailExists(@RequestParam String email) {
        return userService.emailExists(email);
    }

    // Check if phone number exists
    @GetMapping("/exists/phone")
    public boolean phoneNumberExists(@RequestParam String phoneNumber) {
        return userService.phoneNumberExists(phoneNumber);
    }
}
