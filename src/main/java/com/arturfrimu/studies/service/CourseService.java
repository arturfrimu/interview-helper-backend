package com.arturfrimu.studies.service;

import com.arturfrimu.studies.dto.command.Commands.CreateCourseCommand;
import com.arturfrimu.studies.dto.command.Commands.UpdateCourseCommand;
import com.arturfrimu.studies.entity.Course;
import com.arturfrimu.studies.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.arturfrimu.studies.exception.ExceptionContainer.ResourceNotFoundException;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> list() {
        return courseRepository.findAll();
    }

    public Course find(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Course not found with id: %s", id)));
    }

    public Course create(CreateCourseCommand command) {
        var newCourse = new Course(command.name(), command.description());
        return courseRepository.save(newCourse);
    }

    public Course update(Long id, UpdateCourseCommand command) {
        var existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Course not found with id: %s", id)));

        existingCourse.setName(command.name());
        existingCourse.setDescription(command.description());

        return courseRepository.save(existingCourse);
    }

    public void delete(Long id) {
        var course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Course not found by id: %s", id)));

        courseRepository.delete(course);
    }
}
