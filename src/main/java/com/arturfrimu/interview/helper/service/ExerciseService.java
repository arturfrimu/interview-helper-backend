package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.entity.Exercise;
import com.arturfrimu.interview.helper.exception.ExceptionContainer;
import com.arturfrimu.interview.helper.repository.ChapterRepository;
import com.arturfrimu.interview.helper.repository.CourseRepository;
import com.arturfrimu.interview.helper.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Exercise not found with id: %s", id)));
    }

    public Exercise create(Commands.CreateExerciseCommand command) {
        var existingChapter = chapterRepository.findById(command.chapterId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Chapter not found with id: %s", command.chapterId())));

        var existingCourse = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Course not found with id: %s", command.courseId())));

        var newExercise = new Exercise(command.name(), command.description(), existingCourse, existingChapter);

        return exerciseRepository.save(newExercise);
    }

    public Exercise update(Long id, Commands.UpdateExerciseCommand command) {
        var existingExercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Exercise not found with id: %s", id)));

        var existingChapter = chapterRepository.findById(command.chapterId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Chapter not found with id: %s", command.chapterId())));

        var existingCourse = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Course not found with id: %s", command.courseId())));

        existingExercise.setName(command.name());
        existingExercise.setDescription(command.description());
        existingExercise.setChapter(existingChapter);
        existingExercise.setCourse(existingCourse);

        return exerciseRepository.save(existingExercise);
    }

    public void delete(Long id) {
        var existingExercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Exercise not found with id: %s", id)));

        exerciseRepository.delete(existingExercise);
    }
}
