package com.arturfrimu.studies.dto.command;

import com.arturfrimu.studies.dto.request.UpdateAchievementRequest;

public record UpdateAchievementCommand(String description, Long userId) {

    public static UpdateAchievementCommand valueOf(UpdateAchievementRequest request) {
        return new UpdateAchievementCommand(request.description(), request.userId());
    }
}
