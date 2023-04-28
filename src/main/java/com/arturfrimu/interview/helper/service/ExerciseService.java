package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.dto.command.Commands.CreateExerciseCommand;
import com.arturfrimu.interview.helper.entity.Exercise;
import com.arturfrimu.interview.helper.exception.ExceptionContainer;
import com.arturfrimu.interview.helper.repository.LessonRepository;
import com.arturfrimu.interview.helper.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final LessonRepository lessonRepository;

    public List<Exercise> list() {
        return exerciseRepository.findAll();
    }

    public Exercise find(Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Exercise not found with id: %s", id)));
    }

    public Exercise create(CreateExerciseCommand command) {
        var existingLesson = lessonRepository.findById(command.lessonId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Lesson not found with id: %s", command.lessonId())));

        var newExercise = new Exercise(command.name(), command.description(), existingLesson);

        return exerciseRepository.save(newExercise);
    }

    public Exercise update(Long id, Commands.UpdateExerciseCommand command) {
        var existingExercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Exercise not found with id: %s", id)));

        var existingLesson = lessonRepository.findById(command.lessonId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Lesson not found with id: %s", command.lessonId())));

        existingExercise.setName(command.name());
        existingExercise.setDescription(command.description());
        existingExercise.setLesson(existingLesson);

        return exerciseRepository.save(existingExercise);
    }

    public void delete(Long id) {
        var existingExercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Exercise not found with id: %s", id)));

        exerciseRepository.delete(existingExercise);
    }
}
