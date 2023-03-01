package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Achievement;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.AchievementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AchievementService {

    private final AchievementRepository achievementRepository;

    public List<Achievement> list() {
        return achievementRepository.findAll();
    }

    public Achievement find(Long id) {
        return achievementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Achievement not found with id: %s", id)));
    }

    public List<Achievement> getAchievementsByUserId(Long userId) {
        return achievementRepository.findAllByUser(userId);
    }

    public Achievement create(Achievement achievement) {
        return achievementRepository.save(achievement);
    }

    public Achievement update(Long id, Achievement command) {
        var existingAchievement = achievementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Achievement not found with id: %s", id)));

        existingAchievement.setDescription(command.getDescription());

        return achievementRepository.save(existingAchievement);
    }

    public void delete(Long id) {
        var existingAchievement = achievementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Achievement not found with id: %s", id)));
        achievementRepository.delete(existingAchievement);
    }
}
