package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Achievement;
import com.arturfrimu.studies.repository.AchievementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementService {
    private final AchievementRepository achievementRepository;

    public Achievement createAchievement(Achievement achievement) {
        return achievementRepository.save(achievement);
    }

    public void deleteAchievement(Long id) {
        achievementRepository.deleteById(id);
    }

    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    public Achievement getAchievementById(Long id) {
        return achievementRepository.findById(id).orElse(null);
    }

    public List<Achievement> getAchievementsByUserId(Long userId) {
        return achievementRepository.findAllByUser(userId);
    }

    public Achievement updateAchievement(Long id, Achievement achievement) {
        Achievement existingAchievement = achievementRepository.findById(id).orElse(null);

        if (existingAchievement != null) {
            existingAchievement.setDescription(achievement.getDescription());
            return achievementRepository.save(existingAchievement);
        }

        return null;
    }
}
