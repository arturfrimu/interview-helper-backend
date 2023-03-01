package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Progress;
import com.arturfrimu.studies.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressService {
    private final ProgressRepository progressRepository;

    public List<Progress> list() {
        return progressRepository.findAll();
    }

    public List<Progress> getProgressByUserId(Long userId) {
        return progressRepository.findByUser(userId);
    }

    public void saveOrUpdateProgress(Progress progress) {
        progressRepository.save(progress);
    }

    public void delete(Long progressId) {
        progressRepository.deleteById(progressId);
    }

    public Progress getProgressByUserIdAndTopicIdAndLessonIdAndQuizId(Long userId, Long topicId, Long lessonId, Long quizId) {
        return progressRepository.findByUserAndTopicAndLessonAndQuiz(userId, topicId, lessonId, quizId);
    }
}
