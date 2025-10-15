package com.app.resqlink.controller;

import com.app.resqlink.model.AmbulanceBookingHistory;
import com.app.resqlink.service.AmbulanceBookingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/ambulance-bookings")
public class AmbulanceBookingHistoryController {

    @Autowired
    private AmbulanceBookingHistoryService bookingService;

    @PostMapping("/create")
    public AmbulanceBookingHistory create(@RequestBody AmbulanceBookingHistory booking) {
        return bookingService.createBooking(booking);
    }

    @PostMapping("/assign-driver/{bookingId}")
    public AmbulanceBookingHistory assignDriverAndTrack(
            @PathVariable UUID bookingId,
            @RequestParam UUID driverId,
            @RequestBody Map<String, Double> driverLocation) {
        return bookingService.assignDriverAndTrack(bookingId, driverId, driverLocation);
    }

    @GetMapping("/user/{userId}")
    public List<AmbulanceBookingHistory> getBookingsByUser(@PathVariable UUID userId) {
        return bookingService.getBookingsByUserId(userId);
    }

    @GetMapping("/all")
    public List<AmbulanceBookingHistory> getAllBookingHistories() {
        return bookingService.getAllBookingHistories();
    }
}
