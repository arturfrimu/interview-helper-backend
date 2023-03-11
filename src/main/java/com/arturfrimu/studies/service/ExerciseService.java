package com.arturfrimu.studies.service;

import com.arturfrimu.studies.dto.command.Commands.CreateExerciseCommand;
import com.arturfrimu.studies.dto.command.Commands.UpdateExerciseCommand;
import com.arturfrimu.studies.entity.Exercise;
import com.arturfrimu.studies.repository.ChapterRepository;
import com.arturfrimu.studies.repository.CourseRepository;
import com.arturfrimu.studies.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.arturfrimu.studies.exception.ExceptionContainer.ResourceNotFoundException;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final CourseRepository courseRepository;
    private final ChapterRepository chapterRepository;

    public List<Exercise> list() {
        return exerciseRepository.findAll();
    }

    public Exercise find(Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Exercise not found with id: %s", id)));
    }

    public Exercise create(CreateExerciseCommand command) {
        var existingChapter = chapterRepository.findById(command.chapterId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Chapter not found with id: %s", command.chapterId())));

        var existingCourse = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Course not found with id: %s", command.courseId())));

        var newExercise = new Exercise(command.name(), command.description(), existingCourse, existingChapter);

        return exerciseRepository.save(newExercise);
    }

    public Exercise update(Long id, UpdateExerciseCommand command) {
        var existingExercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Exercise not found with id: %s", id)));

        var existingChapter = chapterRepository.findById(command.chapterId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Chapter not found with id: %s", command.chapterId())));

        var existingCourse = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Course not found with id: %s", command.courseId())));

        existingExercise.setName(command.name());
        existingExercise.setDescription(command.description());
        existingExercise.setChapter(existingChapter);
        existingExercise.setCourse(existingCourse);

        return exerciseRepository.save(existingExercise);
    }

    public void delete(Long id) {
        var existingExercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Exercise not found with id: %s", id)));

        exerciseRepository.delete(existingExercise);
    }
}
