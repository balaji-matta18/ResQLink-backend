package com.app.resqlink.controller;

import com.app.resqlink.model.Review;
import com.app.resqlink.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/create/{reviewerId}")
    public Review createReview(@PathVariable UUID reviewerId, @RequestBody Review review) {
        return reviewService.createReview(reviewerId, review);
    }

    @GetMapping("/{reviewId}")
    public Review getReviewById(@PathVariable UUID reviewId) {
        return reviewService.getReviewById(reviewId);
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/reviewer/{reviewerId}")
    public List<Review> getReviewsByReviewer(@PathVariable UUID reviewerId) {
        return reviewService.getReviewsByReviewer(reviewerId);
    }

    @GetMapping("/target/{targetId}")
    public List<Review> getReviewsByTarget(
            @PathVariable UUID targetId,
            @RequestParam String type) {
        return reviewService.getReviewsByTarget(targetId, type);
    }

    @PostMapping("/update/{reviewId}")
    public Review updateReview(@PathVariable UUID reviewId, @RequestBody Review review) {
        return reviewService.updateReview(reviewId, review);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable UUID reviewId) {
        reviewService.deleteReview(reviewId);
    }
}
