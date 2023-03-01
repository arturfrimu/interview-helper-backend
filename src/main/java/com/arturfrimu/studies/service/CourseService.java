package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Course;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new ResourceNotFoundException("Course"));
    }

    public Course create(Course course) {
        return courseRepository.save(course);
    }

    public Course update(Long id, Course courseDetails) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course"));

        course.setName(courseDetails.getName());
        course.setDescription(courseDetails.getDescription());

        return courseRepository.save(course);
    }

    public void delete(Long id) {
        var course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Course not found by id: %s", id)));
        courseRepository.delete(course);
    }
}
