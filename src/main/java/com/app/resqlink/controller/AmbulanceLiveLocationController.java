package com.app.resqlink.controller;

import com.app.resqlink.model.AmbulanceLiveLocation;
import com.app.resqlink.service.AmbulanceLiveLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/ambulance-tracking")
public class AmbulanceLiveLocationController {

    @Autowired
    private AmbulanceLiveLocationService locationService;

    @GetMapping("/{bookingId}")
    public AmbulanceLiveLocation getLocation(@PathVariable UUID bookingId) {
        return locationService.getLiveLocation(bookingId);
    }

    @PostMapping("/{bookingId}")
    public AmbulanceLiveLocation updateLocation(
            @PathVariable UUID bookingId,
            @RequestBody Map<String, Double> newLocation) {
        return locationService.updateLocation(bookingId, newLocation);
    }
}
