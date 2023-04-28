package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.dto.command.Commands.CreateAchievementCommand;
import com.arturfrimu.interview.helper.dto.command.Commands.UpdateAchievementCommand;
import com.arturfrimu.interview.helper.entity.Achievement;
import com.arturfrimu.interview.helper.exception.ExceptionContainer;
import com.arturfrimu.interview.helper.repository.AchievementRepository;
import com.arturfrimu.interview.helper.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AchievementService {

    private final AchievementRepository achievementRepository;
    private final LessonRepository lessonRepository;

    public List<Achievement> list() {
        return achievementRepository.findAll();
    }

    public Achievement find(Long id) {
        return achievementRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Achievement not found with id: %s", id)));
    }

    public List<Achievement> findByLessonId(Long lessonId) {
        return achievementRepository.findAchievementByLessonLessonId(lessonId);
    }

    public Achievement create(CreateAchievementCommand command) {
        var lesson = lessonRepository.findById(command.lessonId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Lesson not found with id: %s", command.lessonId())));

        var newAchievement = new Achievement(command.description(), lesson);

        return achievementRepository.save(newAchievement);
    }

    public Achievement update(Long id, UpdateAchievementCommand command) {
        var existingAchievement = achievementRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Achievement not found with id: %s", id)));

        var lesson = lessonRepository.findById(command.lessonId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Lesson not found with id: %s", command.lessonId())));

        existingAchievement.setDescription(command.description());
        existingAchievement.setLesson(lesson);

        return achievementRepository.save(existingAchievement);
    }

    public void delete(Long id) {
        var existingAchievement = achievementRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Achievement not found with id: %s", id)));

        achievementRepository.delete(existingAchievement);
    }
}
