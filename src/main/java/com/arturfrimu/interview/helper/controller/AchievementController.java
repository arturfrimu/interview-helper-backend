package com.arturfrimu.interview.helper.controller;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.entity.Achievement;
import com.arturfrimu.interview.helper.dto.request.Requests.CreateAchievementRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.UpdateAchievementRequest;
import com.arturfrimu.interview.helper.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("*")
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

    @GetMapping("/lessons/{lessonId}")
    public ResponseEntity<List<Achievement>> findByLessonId(@PathVariable Long lessonId) {
        var achievements = achievementService.findByLessonId(lessonId);
        return ok(achievements);
    }

    @PostMapping
    public ResponseEntity<Achievement> create(@RequestBody CreateAchievementRequest request) {
        var command = of(request).map(Commands.CreateAchievementCommand::valueOf).get();
        var createdAchievement = achievementService.create(command);
        return ok(createdAchievement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Achievement> update(@PathVariable Long id, @RequestBody UpdateAchievementRequest request) {
        var command = of(request).map(Commands.UpdateAchievementCommand::valueOf).get();
        var updatedAchievement = achievementService.update(id, command);
        return ok(updatedAchievement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        achievementService.delete(id);
        return ok().build();
    }
}
