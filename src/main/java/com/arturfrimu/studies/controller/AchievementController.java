package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.dto.command.CreateAchievementCommand;
import com.arturfrimu.studies.dto.command.UpdateAchievementCommand;
import com.arturfrimu.studies.dto.request.CreateAchievementRequest;
import com.arturfrimu.studies.dto.request.UpdateAchievementRequest;
import com.arturfrimu.studies.entity.Achievement;
import com.arturfrimu.studies.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Optional.of;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/achievements")
@RequiredArgsConstructor
public class AchievementController {

    private final AchievementService achievementService;

    @GetMapping
    public ResponseEntity<List<Achievement>> list() {
        var achievements = achievementService.list();
        return ok(achievements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Achievement> find(@PathVariable Long id) {
        var achievement = achievementService.find(id);
        return ok(achievement);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Achievement>> findAchievementsByUserId(@PathVariable Long userId) {
        var achievements = achievementService.getAchievementsByUserId(userId);
        return ok(achievements);
    }

    @PostMapping
    public ResponseEntity<Achievement> create(@RequestBody CreateAchievementRequest request) {
        var command = of(request).map(CreateAchievementCommand::valueOf).get();
        var createdAchievement = achievementService.create(command);
        return ok(createdAchievement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Achievement> update(@PathVariable Long id, @RequestBody UpdateAchievementRequest request) {
        var command = of(request).map(UpdateAchievementCommand::valueOf).get();
        var updatedAchievement = achievementService.update(id, command);
        return ok(updatedAchievement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        achievementService.delete(id);
        return ok().build();
    }
}
