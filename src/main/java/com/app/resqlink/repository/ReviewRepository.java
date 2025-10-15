package com.app.resqlink.repository;

import com.app.resqlink.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByReviewerUserId(UUID reviewerId);

    List<Review> findByTargetId(UUID targetId);

    List<Review> findByTargetIdAndTargetType(UUID targetId, String targetType);
}
