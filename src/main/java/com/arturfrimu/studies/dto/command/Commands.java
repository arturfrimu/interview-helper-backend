package com.arturfrimu.studies.dto.command;

import com.arturfrimu.studies.dto.request.Requests.CreateAchievementRequest;
import com.arturfrimu.studies.dto.request.Requests.CreateChapterRequest;
import com.arturfrimu.studies.dto.request.Requests.CreateCourseRequest;
import com.arturfrimu.studies.dto.request.Requests.CreateForumRequest;
import com.arturfrimu.studies.dto.request.Requests.CreateTopicRequest;
import com.arturfrimu.studies.dto.request.Requests.CreateUserRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateAchievementRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateCourseRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateForumRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateTopicRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateUserRequest;

import static com.arturfrimu.studies.dto.request.Requests.UpdateChapterRequest;

public final class Commands {

    public record CreateAchievementCommand(String description, Long userId) {

        public static CreateAchievementCommand valueOf(CreateAchievementRequest request) {
            return new CreateAchievementCommand(request.description(), request.userId());
        }
    }

    public record UpdateAchievementCommand(String description, Long userId) {

        public static UpdateAchievementCommand valueOf(UpdateAchievementRequest request) {
            return new UpdateAchievementCommand(request.description(), request.userId());
        }
    }

    public record CreateForumCommand(String name, String description) {

        public static CreateForumCommand valueOf(CreateForumRequest request) {
            return new CreateForumCommand(request.name(), request.description());
        }
    }

    public record UpdateForumCommand(String name, String description) {

        public static UpdateForumCommand valueOf(UpdateForumRequest request) {
            return new UpdateForumCommand(request.name(), request.description());
        }
    }

    public record CreateUserCommand(String name, String email) {

        public static CreateUserCommand valueOf(CreateUserRequest request) {
            return new CreateUserCommand(request.name(), request.email());
        }
    }

    public record UpdateUserCommand(String name, String email) {

        public static UpdateUserCommand valueOf(UpdateUserRequest request) {
            return new UpdateUserCommand(request.name(), request.email());
        }
    }

    public record CreateTopicCommand(String name, String description) {

        public static CreateTopicCommand valueOf(CreateTopicRequest request) {
            return new CreateTopicCommand(request.name(), request.description());
        }
    }

    public record UpdateTopicCommand(String name, String description) {

        public static UpdateTopicCommand valueOf(UpdateTopicRequest request) {
            return new UpdateTopicCommand(request.name(), request.description());
        }
    }

    public record CreateCourseCommand(String name, String description) {

        public static CreateCourseCommand valueOf(CreateCourseRequest request) {
            return new CreateCourseCommand(request.name(), request.description());
        }
    }

    public record UpdateCourseCommand(String name, String description) {

        public static UpdateCourseCommand valueOf(UpdateCourseRequest request) {
            return new UpdateCourseCommand(request.name(), request.description());
        }
    }

    public record CreateChapterCommand(String name, String description, Long courseId) {

        public static CreateChapterCommand valueOf(CreateChapterRequest request) {
            return new CreateChapterCommand(request.name(), request.description(), request.courseId());
        }
    }

    public record UpdateChapterCommand(String name, String description, Long courseId) {

        public static UpdateChapterCommand valueOf(UpdateChapterRequest request) {
            return new UpdateChapterCommand(request.name(), request.description(), request.courseId());
        }
    }
}
