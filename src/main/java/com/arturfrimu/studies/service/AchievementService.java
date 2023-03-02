package com.arturfrimu.studies.service;

import com.arturfrimu.studies.dto.command.CreateAchievementCommand;
import com.arturfrimu.studies.dto.command.UpdateAchievementCommand;
import com.arturfrimu.studies.entity.Achievement;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.AchievementRepository;
import com.arturfrimu.studies.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AchievementService {

    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;

    public List<Achievement> list() {
        return achievementRepository.findAll();
    }

    public Achievement find(Long id) {
        return achievementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Achievement not found with id: %s", id)));
    }

    public List<Achievement> getAchievementsByUserId(Long userId) {
        return achievementRepository.findAchievementByUserUserId(userId);
    }

    public Achievement create(CreateAchievementCommand command) {
        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new ResourceNotFoundException(format("User not found with id: %s", command.userId())));

        var newAchievement = new Achievement(command.description(), user);

        return achievementRepository.save(newAchievement);
    }

    public Achievement update(Long id, UpdateAchievementCommand command) {
        var existingAchievement = achievementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Achievement not found with id: %s", id)));

        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new ResourceNotFoundException(format("User not found with id: %s", command.userId())));

        existingAchievement.setDescription(command.description());
        existingAchievement.setUser(user);

        return achievementRepository.save(existingAchievement);
    }

    public void delete(Long id) {
        var existingAchievement = achievementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Achievement not found with id: %s", id)));
        achievementRepository.delete(existingAchievement);
    }
}
