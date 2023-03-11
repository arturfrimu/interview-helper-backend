package com.arturfrimu.studies.dto.request;

public final class Requests {

    //@formatter:off

    public record CreateAchievementRequest(String description, Long userId) {}

    public record UpdateAchievementRequest(String description, Long userId) {}

    public record CreateForumRequest(String name, String description) {}

    public record UpdateForumRequest(String name, String description) {}

    public record CreateUserRequest(String name, String email) {}

    public record UpdateUserRequest(String name, String email) {}

    public record CreateTopicRequest(String name, String description ) {}

    public record UpdateTopicRequest(String name, String description ) {}

    public record CreateCourseRequest(String name, String description ) {}

    public record UpdateCourseRequest(String name, String description ) {}

    public record CreateChapterRequest(String name, String description, Long courseId) {}

    public record UpdateChapterRequest(String name, String description, Long courseId) {}

    public record CreatePostRequest(String title, String content, Long forumId) {}

    public record UpdatePostRequest(String title, String content, Long forumId) {}

    public record CreateSectionRequest(String name, String description, Long courseId, Long chapterId) {}

    public record UpdateSectionRequest(String name, String description, Long courseId, Long chapterId) {}

    public record CreateProjectRequest(String name, String description, Long courseId, Long sectionId) {}

    public record UpdateProjectRequest(String name, String description, Long courseId, Long sectionId) {}

    public record CreateCommentRequest(String content, Long userId, Long postId) {}

    public record UpdateCommentRequest(String content, Long userId, Long postId) {}

    public record CreateExerciseRequest(String name, String description, Long courseId, Long chapterId) {}

    public record UpdateExerciseRequest(String name, String description, Long courseId, Long chapterId) {}

    public record CreateLessonRequest(String name, String description, Long topicId) {}

    public record UpdateLessonRequest(String name, String description, Long topicId) {}

    public record CreateQuizRequest(String name, String description, Long lessonId) {}

    public record UpdateQuizRequest(String name, String description, Long lessonId) {}

    //@formatter:oon
}
