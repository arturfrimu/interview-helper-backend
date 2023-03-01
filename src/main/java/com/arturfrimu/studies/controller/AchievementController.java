package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.entity.Achievement;
import com.arturfrimu.studies.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/achievement")
@RequiredArgsConstructor
public class AchievementController {
    private final AchievementService achievementService;

    @GetMapping("")
    public List<Achievement> getAllAchievements() {
        return achievementService.getAllAchievements();
    }

    @GetMapping("/{id}")
    public Achievement getAchievementById(@PathVariable Long id) {
        return achievementService.getAchievementById(id);
    }

    @GetMapping("/users/{userId}")
    public List<Achievement> getAchievementsByUserId(@PathVariable Long userId) {
        return achievementService.getAchievementsByUserId(userId);
    }

    @PostMapping("")
    public Achievement createAchievement(@RequestBody Achievement achievement) {
        return achievementService.createAchievement(achievement);
    }

    @PutMapping("/{id}")
    public Achievement updateAchievement(@PathVariable Long id, @RequestBody Achievement achievement) {
        return achievementService.updateAchievement(id, achievement);
    }

    @DeleteMapping("/{id}")
    public void deleteAchievement(@PathVariable Long id) {
        achievementService.deleteAchievement(id);
    }
}
