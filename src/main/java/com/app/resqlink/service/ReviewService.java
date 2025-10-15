package com.app.resqlink.service;

import com.app.resqlink.model.Review;
import com.app.resqlink.model.User;
import com.app.resqlink.repository.ReviewRepository;
import com.app.resqlink.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public Review createReview(UUID reviewerId, Review review) {
        if (review == null) {
            throw new IllegalArgumentException("Review data cannot be null.");
        }
        if (review.getRating() == null || review.getRating() < 1 || review.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        if (review.getTargetId() == null || review.getTargetType() == null) {
            throw new IllegalArgumentException("Target ID and type must be provided.");
        }

        User reviewer = userRepository.findById(reviewerId)
                .orElseThrow(() -> new RuntimeException("Reviewer not found with ID: " + reviewerId));

        boolean targetUserExists = userRepository.existsById(review.getTargetId());

        if (!targetUserExists) {
            throw new IllegalArgumentException("Target user not found with ID: " + review.getTargetId());
        }

        if (review.getTargetId().equals(reviewerId)) {
            throw new IllegalArgumentException("Users cannot review themselves.");
        }

        review.setReviewer(reviewer);
        return reviewRepository.save(review);
    }

    public Review getReviewById(UUID reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with ID: " + reviewId));
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByReviewer(UUID reviewerId) {
        return reviewRepository.findByReviewerUserId(reviewerId);
    }

    public List<Review> getReviewsByTarget(UUID targetId, String targetType) {
        return reviewRepository.findByTargetIdAndTargetType(targetId, targetType);
    }

    public Review updateReview(UUID reviewId, Review updatedReview) {
        if (updatedReview == null) {
            throw new IllegalArgumentException("Updated review data cannot be null.");
        }

        if (updatedReview.getRating() == null || updatedReview.getRating() < 1 || updatedReview.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }

        Review existing = getReviewById(reviewId);

        if (updatedReview.getComment() != null && !updatedReview.getComment().trim().isEmpty()) {
            existing.setComment(updatedReview.getComment());
        }

        existing.setRating(updatedReview.getRating());

        return reviewRepository.save(existing);
    }

    public void deleteReview(UUID reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new RuntimeException("Review not found for ID: " + reviewId);
        }
        reviewRepository.deleteById(reviewId);
    }
}
