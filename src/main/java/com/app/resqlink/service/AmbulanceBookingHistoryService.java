package com.app.resqlink.service;

import com.app.resqlink.model.AmbulanceBookingHistory;
import com.app.resqlink.model.AmbulanceLiveLocation;
import com.app.resqlink.model.User;
import com.app.resqlink.repository.AmbulanceBookingHistoryRepository;
import com.app.resqlink.repository.AmbulanceLiveLocationRepository;
import com.app.resqlink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AmbulanceBookingHistoryService {

    @Autowired
    private AmbulanceBookingHistoryRepository bookingRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AmbulanceLiveLocationRepository liveLocationRepo;

    /**
     * Create a new ambulance booking
     */
    public AmbulanceBookingHistory createBooking(AmbulanceBookingHistory booking) {
        UUID userId = booking.getUser() != null ? booking.getUser().getUserId() : null;

        if (userId == null) {
            throw new IllegalArgumentException("User ID is required.");
        }

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        booking.setUser(user);

        return bookingRepo.save(booking);
    }

    /**
     * Assign a driver (via driverId) and start live tracking
     */
    public AmbulanceBookingHistory assignDriverAndTrack(UUID bookingId, UUID driverId,
                                                        Map<String, Double> driverLocation) {
        AmbulanceBookingHistory booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setDriverId(driverId);
        booking.setStatus(AmbulanceBookingHistory.BookingStatus.PROGRESS);
        bookingRepo.save(booking);

        AmbulanceLiveLocation liveLocation = new AmbulanceLiveLocation();
        liveLocation.setBooking(booking);
        liveLocation.setCurrentLocation(driverLocation);
        liveLocationRepo.save(liveLocation);

        return booking;
    }

    /**
     * Get all bookings made by a specific user
     */
    public List<AmbulanceBookingHistory> getBookingsByUserId(UUID userId) {
        return bookingRepo.findByUserUserId(userId);
    }

    /**
     * Get all ambulance booking histories in the system
     */
    public List<AmbulanceBookingHistory> getAllBookingHistories() {
        return bookingRepo.findAll();
    }
}
