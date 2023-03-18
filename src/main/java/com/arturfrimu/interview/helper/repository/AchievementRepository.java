package com.arturfrimu.interview.helper.repository;

import com.arturfrimu.interview.helper.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    List<Achievement> findAchievementByUserUserId(Long userId);
}
