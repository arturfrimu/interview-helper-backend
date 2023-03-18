package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.entity.Achievement;
import com.arturfrimu.interview.helper.exception.ExceptionContainer;
import com.arturfrimu.interview.helper.repository.AchievementRepository;
import com.arturfrimu.interview.helper.repository.UserRepository;
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
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Achievement not found with id: %s", id)));
    }

    public List<Achievement> getAchievementsByUserId(Long userId) {
        return achievementRepository.findAchievementByUserUserId(userId);
    }

    public Achievement create(Commands.CreateAchievementCommand command) {
        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("User not found with id: %s", command.userId())));

        var newAchievement = new Achievement(command.description(), user);

        return achievementRepository.save(newAchievement);
    }

    public Achievement update(Long id, Commands.UpdateAchievementCommand command) {
        var existingAchievement = achievementRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Achievement not found with id: %s", id)));

        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("User not found with id: %s", command.userId())));

        existingAchievement.setDescription(command.description());
        existingAchievement.setUser(user);

        return achievementRepository.save(existingAchievement);
    }

    public void delete(Long id) {
        var existingAchievement = achievementRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Achievement not found with id: %s", id)));

        achievementRepository.delete(existingAchievement);
    }
}