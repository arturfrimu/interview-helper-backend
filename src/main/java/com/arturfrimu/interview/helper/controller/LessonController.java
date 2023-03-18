package com.arturfrimu.interview.helper.controller;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.dto.request.Requests.CreateLessonRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.UpdateLessonRequest;
import com.arturfrimu.interview.helper.entity.Lesson;
import com.arturfrimu.interview.helper.service.LessonService;
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
    public ResponseEntity<Lesson> create(@RequestBody CreateLessonRequest body) {
        var command = of(body).map(Commands.CreateLessonCommand::valueOf).get();
        var createdLesson = lessonService.create(command);
        return ok(createdLesson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lesson> update(@PathVariable Long id, @RequestBody UpdateLessonRequest body) {
        var command = of(body).map(Commands.UpdateLessonCommand::valueOf).get();
        var updatedLesson = lessonService.update(id, command);
        return ok(updatedLesson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lessonService.delete(id);
        return ok().build();
    }
}