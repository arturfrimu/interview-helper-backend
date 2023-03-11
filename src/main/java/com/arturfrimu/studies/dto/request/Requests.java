package com.arturfrimu.studies.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public final class Requests {

    //@formatter:off

    public record CreateAchievementRequest(@NotBlank String description, Long userId) {}

    public record UpdateAchievementRequest(@NotBlank String description, Long userId) {}

    public record CreateForumRequest(@NotBlank String name, @NotBlank String description) {}

    public record UpdateForumRequest(@NotBlank String name, @NotBlank String description) {}

    public record CreateUserRequest(@NotBlank String name, @Email String email) {}

    public record UpdateUserRequest(@NotBlank String name, @Email String email) {}

    public record CreateTopicRequest(
            @NotBlank(message = "The name of Topic can not be null") String name,
            @NotBlank(message = "The description of Topic can not be null") String description
    ) {}

    public record UpdateTopicRequest(
            @NotBlank(message = "The name of Topic can not be null") String name,
            @NotBlank(message = "The description of Topic can not be null") String description
    ) {}

    public record CreateCourseRequest(@NotBlank String name, @NotBlank String description) {}

    public record UpdateCourseRequest(@NotBlank String name, @NotBlank String description) {}

    public record CreateChapterRequest(@NotBlank String name, @NotBlank String description, Long courseId) {}

    public record UpdateChapterRequest(@NotBlank String name, @NotBlank String description, Long courseId) {}

    public record CreatePostRequest(@NotBlank String title, @NotBlank String content, Long forumId) {}

    public record UpdatePostRequest(@NotBlank String title, @NotBlank String content, Long forumId) {}

    public record CreateSectionRequest(@NotBlank String name, @NotBlank String description, Long courseId, Long chapterId) {}

    public record UpdateSectionRequest(@NotBlank String name, @NotBlank String description, Long courseId, Long chapterId) {}

    public record CreateProjectRequest(@NotBlank String name, @NotBlank String description, Long courseId, Long sectionId) {}

    public record UpdateProjectRequest(@NotBlank String name, @NotBlank String description, Long courseId, Long sectionId) {}

    public record CreateCommentRequest(@NotBlank String content, Long userId, Long postId) {}

    public record UpdateCommentRequest(@NotBlank String content, Long userId, Long postId) {}

    public record CreateExerciseRequest(@NotBlank String name, @NotBlank String description, Long courseId, Long chapterId) {}

    public record UpdateExerciseRequest(@NotBlank String name, @NotBlank String description, Long courseId, Long chapterId) {}

    public record CreateLessonRequest(@NotBlank String name, @NotBlank String description, Long topicId) {}

    public record UpdateLessonRequest(@NotBlank String name, @NotBlank String description, Long topicId) {}

    public record CreateQuizRequest(@NotBlank String name, @NotBlank String description, Long lessonId) {}

    public record UpdateQuizRequest(@NotBlank String name, @NotBlank String description, Long lessonId) {}

    //@formatter:oon
}
