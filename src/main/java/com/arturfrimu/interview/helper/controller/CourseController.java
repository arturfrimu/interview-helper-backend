package com.arturfrimu.interview.helper.controller;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.dto.request.Requests.CreateCourseRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.UpdateCourseRequest;
import com.arturfrimu.interview.helper.entity.Course;
import com.arturfrimu.interview.helper.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Optional.of;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> list() {
        var courses = courseService.list();
        return ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> find(@PathVariable Long id) {
        var course = courseService.find(id);
        return ok(course);
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody CreateCourseRequest body) {
        var command = of(body).map(Commands.CreateCourseCommand::valueOf).get();
        var createdCourse = courseService.create(command);
        return ok(createdCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody UpdateCourseRequest body) {
        var command = of(body).map(Commands.UpdateCourseCommand::valueOf).get();
        var updatedCourse = courseService.update(id, command);
        return ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.delete(id);
        return ok().build();
    }
}
