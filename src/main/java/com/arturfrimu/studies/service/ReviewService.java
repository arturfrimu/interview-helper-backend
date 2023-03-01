package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Review;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> list() {
        return reviewRepository.findAll();
    }

    public Review find(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review"));
    }

    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    public Review update(Long id, Review reviewDetails) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review"));

        review.setContent(reviewDetails.getContent());
        review.setRating(reviewDetails.getRating());
        review.setCourse(reviewDetails.getCourse());
        review.setLesson(reviewDetails.getLesson());
        review.setQuiz(reviewDetails.getQuiz());
        review.setExercise(reviewDetails.getExercise());
        review.setProject(reviewDetails.getProject());

        return reviewRepository.save(review);
    }

    public void delete(Long id) {
        var existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Review not found with id: %s", id)));
        reviewRepository.delete(existingReview);
    }
}
