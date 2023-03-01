package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.entity.Lesson;
import com.arturfrimu.studies.service.LessonService;
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
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<List<Lesson>> list() {
        var lessons = lessonService.list();
        return ok(lessons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> find(@PathVariable Long id) {
        var lesson = lessonService.find(id);
        return ok(lesson);
    }

    @PostMapping
    public ResponseEntity<Lesson> create(@RequestBody Lesson lesson) {
        var createdLesson = lessonService.create(lesson);
        return ok(createdLesson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lesson> update(@PathVariable Long id, @RequestBody Lesson lesson) {
        var updatedLesson = lessonService.update(id, lesson);
        return ok(updatedLesson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lessonService.delete(id);
        return ok().build();
    }
}