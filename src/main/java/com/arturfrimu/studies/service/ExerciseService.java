package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Exercise;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public List<Exercise> list() {
        return exerciseRepository.findAll();
    }

    public Exercise find(Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Exercise not found with id: %s", id)));
    }

    public Exercise create(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Exercise update(Long id, Exercise command) {
        var existingExercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Exercise not found with id: %s", id)));

        existingExercise.setName(command.getName());
        existingExercise.setDescription(command.getDescription());
        existingExercise.setCourse(command.getCourse());
        existingExercise.setChapter(command.getChapter());

        return exerciseRepository.save(existingExercise);
    }

    public void delete(Long id) {
        var existingExercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Exercise not found with id: %s", id)));
        exerciseRepository.delete(existingExercise);
    }
}
