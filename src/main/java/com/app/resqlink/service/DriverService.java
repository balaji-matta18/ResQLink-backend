package com.app.resqlink.service;

import com.app.resqlink.model.Driver;
import com.app.resqlink.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Optional<Driver> getDriverById(UUID id) {
        return driverRepository.findById(id);
    }

    public Driver updateDriver(UUID id, Driver updatedDriver) {
        return driverRepository.findById(id).map(driver -> {
            driver.setFirstName(updatedDriver.getFirstName());
            driver.setLastName(updatedDriver.getLastName());
            driver.setPhoneNumber(updatedDriver.getPhoneNumber());
            driver.setEmail(updatedDriver.getEmail());
            driver.setAge(updatedDriver.getAge());
            driver.setGender(updatedDriver.getGender());
            driver.setAddress(updatedDriver.getAddress());
            driver.setLicenseNumber(updatedDriver.getLicenseNumber());
            driver.setVehicleNumber(updatedDriver.getVehicleNumber());
            driver.setAvailable(updatedDriver.isAvailable());
            driver.setNumberOfRidesCompleted(updatedDriver.getNumberOfRidesCompleted());
            driver.setGpsLocation(updatedDriver.getGpsLocation());
            return driverRepository.save(driver);
        }).orElse(null);
    }

    public boolean deleteDriver(UUID id) {
        if (driverRepository.existsById(id)) {
            driverRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
