package com.app.resqlink.repository;

import com.app.resqlink.model.DishaProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DishaRepository extends JpaRepository<DishaProfile, UUID> {
}
