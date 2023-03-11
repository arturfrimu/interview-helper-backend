package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Review;
import com.arturfrimu.studies.exception.ExceptionContainer.ResourceNotFoundException;
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
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Review not found with id: %s", id)));
    }

    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    public Review update(Long id, Review command) {
        var existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Review not found with id: %s", id)));

        existingReview.setContent(command.getContent());
        existingReview.setRating(command.getRating());
        existingReview.setCourse(command.getCourse());
        existingReview.setLesson(command.getLesson());
        existingReview.setQuiz(command.getQuiz());
        existingReview.setExercise(command.getExercise());
        existingReview.setProject(command.getProject());

        return reviewRepository.save(existingReview);
    }

    public void delete(Long id) {
        var existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Review not found with id: %s", id)));

        reviewRepository.delete(existingReview);
    }
}
