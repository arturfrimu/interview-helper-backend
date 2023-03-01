package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.entity.Progress;
import com.arturfrimu.studies.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {
    private final ProgressService progressService;

    @GetMapping("/")
    public List<Progress> getAllProgress() {
        return progressService.getAllProgress();
    }

    @GetMapping("/{userId}")
    public List<Progress> getProgressByUserId(@PathVariable Long userId) {
        return progressService.getProgressByUserId(userId);
    }

    @PostMapping("/")
    public void saveOrUpdateProgress(@RequestBody Progress progress) {
        progressService.saveOrUpdateProgress(progress);
    }

    @DeleteMapping("/{progressId}")
    public void deleteProgress(@PathVariable Long progressId) {
        progressService.deleteProgress(progressId);
    }

    @GetMapping("/{userId}/{topicId}/{lessonId}/{quizId}")
    public Progress getProgressByUserIdAndTopicIdAndLessonIdAndQuizId(@PathVariable Long userId, @PathVariable Long topicId, @PathVariable Long lessonId, @PathVariable Long quizId) {
        return progressService.getProgressByUserIdAndTopicIdAndLessonIdAndQuizId(userId, topicId, lessonId, quizId);
    }
}
