package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Exercise;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Optional<Exercise> getExerciseById(Long id) {
        return exerciseRepository.findById(id);
    }

    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Exercise updateExercise(Long id, Exercise exercise) {
        Optional<Exercise> existingExercise = exerciseRepository.findById(id);
        if (existingExercise.isPresent()) {
            Exercise updatedExercise = existingExercise.get();
            updatedExercise.setName(exercise.getName());
            updatedExercise.setDescription(exercise.getDescription());
            updatedExercise.setCourse(exercise.getCourse());
            updatedExercise.setChapter(exercise.getChapter());
            return exerciseRepository.save(updatedExercise);
        } else {
            throw new ResourceNotFoundException("Exercise not found with id " + id);
        }
    }

    public void deleteExercise(Long id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (exercise.isPresent()) {
            exerciseRepository.delete(exercise.get());
        } else {
            throw new ResourceNotFoundException("Exercise not found with id " + id);
        }
    }
}
