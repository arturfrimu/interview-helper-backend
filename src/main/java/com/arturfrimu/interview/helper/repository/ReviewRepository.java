package com.arturfrimu.interview.helper.repository;

import com.arturfrimu.interview.helper.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
