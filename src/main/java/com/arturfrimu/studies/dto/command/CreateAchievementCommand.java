package com.arturfrimu.studies.dto.command;

import com.arturfrimu.studies.dto.request.CreateAchievementRequest;

public record CreateAchievementCommand(String description, Long userId) {

    public static CreateAchievementCommand valueOf(CreateAchievementRequest request) {
        return new CreateAchievementCommand(request.description(), request.userId());
    }
}
