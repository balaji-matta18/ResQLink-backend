package com.app.resqlink.controller;

import com.app.resqlink.model.Driver;
import com.app.resqlink.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/drivers")
@CrossOrigin
public class DriverController {

    @Autowired
    private DriverService driverService;

    // Create a new driver
    @PostMapping("/create")
    public Driver createDriver(@RequestBody Driver driver) {
        return driverService.createDriver(driver);
    }

    // Get all drivers
    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    // Get a driver by ID
    @GetMapping("/{id}")
    public Driver getDriverById(@PathVariable UUID id) {
        return driverService.getDriverById(id).orElse(null);
    }

    // Update a driver
    @PostMapping("/update/{id}")
    public Driver updateDriver(@PathVariable UUID id, @RequestBody Driver driver) {
        return driverService.updateDriver(id, driver);
    }

    // Delete a driver
    @DeleteMapping("/{id}")
    public String deleteDriver(@PathVariable UUID id) {
        boolean deleted = driverService.deleteDriver(id);
        return deleted ? "Driver deleted successfully." : "Driver not found.";
    }
}
