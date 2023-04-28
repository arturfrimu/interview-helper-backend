package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.entity.Review;
import com.arturfrimu.interview.helper.exception.ExceptionContainer;
import com.arturfrimu.interview.helper.repository.ReviewRepository;
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
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Review not found with id: %s", id)));
    }

    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    public Review update(Long id, Review command) {
        var existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Review not found with id: %s", id)));

        existingReview.setContent(command.getContent());
        existingReview.setRating(command.getRating());
        existingReview.setLesson(command.getLesson());

        return reviewRepository.save(existingReview);
    }

    public void delete(Long id) {
        var existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Review not found with id: %s", id)));

        reviewRepository.delete(existingReview);
    }
}
