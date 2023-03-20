package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.dto.response.Response.CourseInfoResponse;
import com.arturfrimu.interview.helper.entity.Course;
import com.arturfrimu.interview.helper.repository.CourseRepository;
import com.arturfrimu.interview.helper.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.arturfrimu.interview.helper.exception.ExceptionContainer.ResourceNotFoundException;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TopicRepository topicRepository;

    public List<CourseInfoResponse> list() {
        return courseRepository.findAll().stream().map(CourseInfoResponse::valueOf).toList();
    }

    public CourseInfoResponse find(Long id) {
        return courseRepository.findById(id).map(CourseInfoResponse::valueOf)
                .orElseThrow(() -> new ResourceNotFoundException(format("Course not found with id: %s", id)));
    }

    public CourseInfoResponse create(Commands.CreateCourseCommand command) {
        var existingTopic = topicRepository.findById(command.topicId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Topic not found with id: %s", command.topicId())));

        var newCourse = new Course(command.name(), command.description(), existingTopic);

        return CourseInfoResponse.valueOf(courseRepository.save(newCourse));
    }

    public CourseInfoResponse update(Long id, Commands.UpdateCourseCommand command) {
        var existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Course not found with id: %s", id)));

        var existingTopic = topicRepository.findById(command.topicId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Topic not found with id: %s", command.topicId())));

        var newCourse = new Course(existingCourse.getCourseId(), command.name(), command.description(), existingTopic);

        return CourseInfoResponse.valueOf(courseRepository.save(newCourse));
    }

    public void delete(Long id) {
        var course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Course not found by id: %s", id)));

        courseRepository.delete(course);
    }
}
