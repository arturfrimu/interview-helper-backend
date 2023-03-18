package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.entity.Progress;
import com.arturfrimu.interview.helper.repository.ProgressRepository;
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

    public List<Progress> getProgressByUserId(Long id) {
        return progressRepository.findByUser(id);
    }

    public Progress create(Progress progress) {
        // TODO: 02.03.2023  
        return progressRepository.save(progress);
    }

    public Progress update(Long id, Progress progress) {
        // TODO: 02.03.2023  
        return progressRepository.save(progress);
    }

    public void delete(Long progressId) {
        progressRepository.deleteById(progressId);
    }

    public Progress getProgressByUserIdAndTopicIdAndLessonIdAndQuizId(Long userId, Long topicId, Long lessonId, Long quizId) {
        return progressRepository.findByUserAndTopicAndLessonAndQuiz(userId, topicId, lessonId, quizId);
    }
}
