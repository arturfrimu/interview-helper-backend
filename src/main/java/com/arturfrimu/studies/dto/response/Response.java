package com.arturfrimu.studies.dto.response;

import com.arturfrimu.studies.entity.Project;
import com.arturfrimu.studies.entity.Quiz;
import com.arturfrimu.studies.entity.Topic;

public final class Response {

    public record ProjectInfoResponse(Long projectId, String name, String description) {

        public static ProjectInfoResponse valueOf(Project response) {
            return new ProjectInfoResponse(response.getProjectId(), response.getName(), response.getDescription());
        }
    }

    public record TopicInfoResponse(Long topicId, String name, String description, Long projectId) {

        public static TopicInfoResponse valueOf(Topic response) {
            return new TopicInfoResponse(response.getTopicId(), response.getName(), response.getDescription(), response.getProject().getProjectId());
        }
    }

    public record QuizInfoResponse(Long quizId, String name, String description, Long lessonId) {

        public static QuizInfoResponse valueOf(Quiz response) {
            return new QuizInfoResponse(response.getQuizId(), response.getName(), response.getDescription(), response.getLesson().getLessonId());
        }
    }
}
