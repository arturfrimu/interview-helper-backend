package com.arturfrimu.interview.helper.repository;

import com.arturfrimu.interview.helper.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findByUser(Long userId);

    Progress findByUserAndTopicAndLessonAndQuiz(Long userId, Long topicId, Long lessonId, Long quizId);
}
