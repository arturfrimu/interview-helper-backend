package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.entity.Course;
import com.arturfrimu.studies.service.CourseService;
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

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<Course> list() {
        return courseService.list();
    }

    @GetMapping("/{id}")
    public Course find(@PathVariable Long id) {
        return courseService.find(id);
    }

    @PostMapping
    public Course create(@RequestBody Course course) {
        return courseService.create(course);
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Long id, @RequestBody Course courseDetails) {
        return courseService.update(id, courseDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.delete(id);
        return ok().build();
    }
}
