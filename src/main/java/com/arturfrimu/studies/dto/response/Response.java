package com.arturfrimu.studies.dto.response;

import com.arturfrimu.studies.entity.Quiz;

public final class Response {

    public record QuizInfoResponse(Long quizId, String name, String description, Long lessonId) {

        public static QuizInfoResponse valueOf(Quiz response) {
            return new QuizInfoResponse(response.getQuizId(), response.getName(), response.getDescription(), response.getLesson().getLessonId());
        }
    }
}
