package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.entity.Review;
import com.arturfrimu.studies.service.ReviewService;
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
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public List<Review> list() {
        return reviewService.list();
    }

    @GetMapping("/reviews/{id}")
    public Review find(@PathVariable(value = "id") Long id) {
        return reviewService.find(id);
    }

    @PostMapping("/reviews")
    public Review create(@RequestBody Review review) {
        return reviewService.create(review);
    }

    @PutMapping("/reviews/{id}")
    public Review update(@PathVariable(value = "id") Long id, @RequestBody Review reviewDetails) {
        return reviewService.update(id, reviewDetails);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        reviewService.delete(id);
        return ok().build();
    }
}
