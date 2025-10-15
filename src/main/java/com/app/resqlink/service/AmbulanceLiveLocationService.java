package com.app.resqlink.service;

import com.app.resqlink.model.AmbulanceLiveLocation;
import com.app.resqlink.repository.AmbulanceLiveLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class AmbulanceLiveLocationService {

    @Autowired
    private AmbulanceLiveLocationRepository liveLocationRepo;

    public AmbulanceLiveLocation updateLocation(UUID bookingId, Map<String, Double> newLocation) {
        AmbulanceLiveLocation live = liveLocationRepo.findByBookingBookingId(bookingId);
        if (live == null)
            throw new RuntimeException("Live location not found");
        live.setCurrentLocation(newLocation);
        return liveLocationRepo.save(live);
    }

    public AmbulanceLiveLocation getLiveLocation(UUID bookingId) {
        return liveLocationRepo.findByBookingBookingId(bookingId);
    }
}
