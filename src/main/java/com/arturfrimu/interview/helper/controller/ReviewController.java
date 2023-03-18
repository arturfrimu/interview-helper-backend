package com.arturfrimu.interview.helper.controller;

import com.arturfrimu.interview.helper.entity.Review;
import com.arturfrimu.interview.helper.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> list() {
        var reviews = reviewService.list();
        return ok(reviews);
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> find(@PathVariable("id") Long id) {
        var review = reviewService.find(id);
        return ok(review);
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> create(@RequestBody Review review) {
        var createdReview = reviewService.create(review);
        return ok(createdReview);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<Review> update(@PathVariable("id") Long id, @RequestBody Review reviewDetails) {
        var updatedReview = reviewService.update(id, reviewDetails);
        return ok(updatedReview);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        reviewService.delete(id);
        return ok().build();
    }
}
