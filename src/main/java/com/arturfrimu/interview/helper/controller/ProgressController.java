package com.arturfrimu.interview.helper.controller;

import com.arturfrimu.interview.helper.entity.Progress;
import com.arturfrimu.interview.helper.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api/progress")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProgressController {

    private final ProgressService progressService;

    @GetMapping
    public ResponseEntity<List<Progress>> list() {
        var progresses = progressService.list();
        return ok(progresses);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Progress>> getProgressByUserId(@PathVariable Long userId) {
        var progresses = progressService.getProgressByUserId(userId);
        return ok(progresses);
    }

    @PostMapping
    public ResponseEntity<Progress> create(@RequestBody Progress progress) {
        var createdProgress = progressService.create(progress);
        return ok(createdProgress);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Progress> update(@PathVariable Long userId, @RequestBody Progress progress) {
        var updatedProgress = progressService.update(userId, progress);
        return ok(updatedProgress);
    }

    @DeleteMapping("/{progressId}")
    public void delete(@PathVariable Long progressId) {
        progressService.delete(progressId);
    }

    @GetMapping("/{userId}/{topicId}/{lessonId}/{quizId}")
    public Progress getProgressByUserIdAndTopicIdAndLessonIdAndQuizId(@PathVariable Long userId, @PathVariable Long topicId, @PathVariable Long lessonId, @PathVariable Long quizId) {
        return progressService.getProgressByUserIdAndTopicIdAndLessonIdAndQuizId(userId, topicId, lessonId, quizId);
    }
}
