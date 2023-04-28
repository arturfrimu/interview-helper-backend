package com.arturfrimu.interview.helper.dto.response;

import com.arturfrimu.interview.helper.entity.Course;
import com.arturfrimu.interview.helper.entity.Lesson;
import com.arturfrimu.interview.helper.entity.Project;
import com.arturfrimu.interview.helper.entity.Quiz;
import com.arturfrimu.interview.helper.entity.Section;
import com.arturfrimu.interview.helper.entity.Task;
import com.arturfrimu.interview.helper.entity.Topic;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public final class Response {

    public record ProjectInfoResponse(Long projectId, String name, String description) {

        public static ProjectInfoResponse valueOf(Project response) {
            return new ProjectInfoResponse(response.getProjectId(), response.getName(), response.getDescription());
        }
    }

    public record ProjectDetailsResponse(
            Long projectId,
            String name,
            String description,
            Set<TopicInfoResponse> topics
    ) {

        public static ProjectDetailsResponse valueOf(Project response) {
            return new ProjectDetailsResponse(
                    response.getProjectId(),
                    response.getName(),
                    response.getDescription(),
                    response.getTopics().stream().map(TopicInfoResponse::valueOf).collect(toSet()));
        }
    }

    public record TopicInfoResponse(Long topicId, String name, String description) {

        public static TopicInfoResponse valueOf(Topic response) {
            return new TopicInfoResponse(response.getTopicId(), response.getName(), response.getDescription());
        }
    }

    public record CourseInfoResponse(Long courseId, String name, String description) {

        public static CourseInfoResponse valueOf(Course response) {
            return new CourseInfoResponse(response.getCourseId(), response.getName(), response.getDescription());
        }
    }

    public record SectionInfoResponse(Long sectionId, String name, String description) {

        public static SectionInfoResponse valueOf(Section response) {
            return new SectionInfoResponse(response.getSectionId(), response.getName(), response.getDescription());
        }
    }

    public record LessonInfoResponse(Long lessonId, String name, String description) {

        public static LessonInfoResponse valueOf(Lesson response) {
            return new LessonInfoResponse(response.getLessonId(), response.getName(), response.getDescription());
        }
    }

    public record QuizInfoResponse(Long quizId, String name, String description) {

        public static QuizInfoResponse valueOf(Quiz response) {
            return new QuizInfoResponse(response.getQuizId(), response.getName(), response.getDescription());
        }
    }

    public record TaskInfoResponse(Long quizId, String name, String description) {

        public static TaskInfoResponse valueOf(Task response) {
            return new TaskInfoResponse(response.getTaskId(), response.getName(), response.getDescription());
        }
    }
}
