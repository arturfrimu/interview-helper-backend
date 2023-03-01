package com.arturfrimu.studies.controller;

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

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/achievement")
@RequiredArgsConstructor
public class AchievementController {

    private final AchievementService achievementService;

    @GetMapping("")
    public List<Achievement> list() {
        return achievementService.list();
    }

    @GetMapping("/{id}")
    public Achievement find(@PathVariable Long id) {
        return achievementService.find(id);
    }

    @GetMapping("/users/{userId}")
    public List<Achievement> getAchievementsByUserId(@PathVariable Long userId) {
        return achievementService.getAchievementsByUserId(userId);
    }

    @PostMapping("")
    public Achievement create(@RequestBody Achievement achievement) {
        return achievementService.create(achievement);
    }

    @PutMapping("/{id}")
    public Achievement update(@PathVariable Long id, @RequestBody Achievement achievement) {
        return achievementService.update(id, achievement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        achievementService.delete(id);
        return ok().build();
    }
}
