package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.entity.Exercise;
import com.arturfrimu.studies.service.ExerciseService;
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
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping
    public List<Exercise> list() {
        return exerciseService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> find(@PathVariable Long id) {
        Optional<Exercise> exercise = exerciseService.find(id);
        return exercise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Exercise create(@RequestBody Exercise exercise) {
        return exerciseService.create(exercise);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> update(@PathVariable Long id, @RequestBody Exercise exercise) {
        Exercise updatedExercise = exerciseService.update(id, exercise);
        return ok(updatedExercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exerciseService.delete(id);
        return ok().build();
    }
}
