package com.arturfrimu.interview.helper.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public final class Requests {

    //@formatter:off

    public record CreateProjectRequest(
            @NotBlank(message = "Name of Project can not be null") String name,
            String description
    ) {}

    public record UpdateProjectRequest(
            @NotBlank(message = "Name of Project can not be null") String name,
            String description
    ) {}

    public record CreateTopicRequest(
            @NotBlank(message = "Name of Topic can not be null") String name,
            String description,
            @NotNull(message = "Topic needs to be assigned to a Project") Long projectId
    ) {}

    public record UpdateTopicRequest(
            @NotBlank(message = "Name of Topic can not be null") String name,
            String description,
            @NotNull(message = "Topic needs to be assigned to a Project") Long projectId
    ) {}

    public record CreateCourseRequest(
            @NotBlank(message = "Name of Course can not be null") String name,
            String description,
            @NotNull Long topicId
    ) {}

    public record UpdateCourseRequest(
            @NotBlank(message = "Name of Course can not be null") String name,
            String description,
            @NotNull Long topicId
    ) {}

    public record CreateChapterRequest(
            @NotBlank(message = "Name of Chapter can not be null") String name,
            String description,
            @NotNull Long courseId
    ) {}

    public record UpdateChapterRequest(
            @NotBlank(message = "Name of Chapter can not be null") String name,
            String description,
            @NotNull Long courseId
    ) {}

    public record CreateSectionRequest(
            @NotBlank(message = "Name of Section can not be null") String name,
            String description,
            @NotNull Long chapterId
    ) {}

    public record UpdateSectionRequest(
            @NotBlank(message = "Name of Section can not be null") String name,
            String description,
            @NotNull Long chapterId
    ) {}

    public record CreateAchievementRequest(@NotBlank String description, Long userId) {}

    public record UpdateAchievementRequest(@NotBlank String description, Long userId) {}

    public record CreateForumRequest(@NotBlank String name, @NotBlank String description) {}

    public record UpdateForumRequest(@NotBlank String name, @NotBlank String description) {}

    public record CreateUserRequest(@NotBlank String name, @Email String email) {}

    public record UpdateUserRequest(@NotBlank String name, @Email String email) {}

    public record CreatePostRequest(@NotBlank String title, @NotBlank String content, Long forumId) {}

    public record UpdatePostRequest(@NotBlank String title, @NotBlank String content, Long forumId) {}

    public record CreateCommentRequest(@NotBlank String content, Long userId, Long postId) {}

    public record UpdateCommentRequest(@NotBlank String content, Long userId, Long postId) {}

    public record CreateExerciseRequest(@NotBlank String name, @NotBlank String description, Long courseId, Long chapterId) {}

    public record UpdateExerciseRequest(@NotBlank String name, @NotBlank String description, Long courseId, Long chapterId) {}

    public record CreateLessonRequest(@NotBlank String name, @NotBlank String description, Long sectionId) {}

    public record UpdateLessonRequest(@NotBlank String name, @NotBlank String description, Long sectionId) {}

    public record CreateQuizRequest(@NotBlank String name, @NotBlank String description, Long lessonId) {}

    public record UpdateQuizRequest(@NotBlank String name, @NotBlank String description, Long lessonId) {}

    //@formatter:oon
}
