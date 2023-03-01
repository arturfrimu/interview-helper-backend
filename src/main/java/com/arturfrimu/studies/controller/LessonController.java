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
    public List<Lesson> list() {
        return lessonService.list();
    }

    @GetMapping("/{id}")
    public Lesson find(@PathVariable Long id) {
        return lessonService.find(id);
    }

    @PostMapping
    public void create(@RequestBody Lesson lesson) {
        lessonService.create(lesson);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Lesson lesson) {
        lessonService.update(id, lesson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lessonService.delete(id);
        return ok().build();
    }
}
