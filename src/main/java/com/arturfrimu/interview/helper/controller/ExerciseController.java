package com.arturfrimu.interview.helper.controller;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.dto.request.Requests.CreateExerciseRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.UpdateExerciseRequest;
import com.arturfrimu.interview.helper.entity.Exercise;
import com.arturfrimu.interview.helper.service.ExerciseService;
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
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping
    public ResponseEntity<List<Exercise>> list() {
        var exercises = exerciseService.list();
        return ok(exercises);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> find(@PathVariable Long id) {
        var exercise = exerciseService.find(id);
        return ok(exercise);
    }

    @PostMapping
    public ResponseEntity<Exercise> create(@RequestBody CreateExerciseRequest body) {
        var command = of(body).map(Commands.CreateExerciseCommand::valueOf).get();
        var createdExercise = exerciseService.create(command);
        return ok(createdExercise);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> update(@PathVariable Long id, @RequestBody UpdateExerciseRequest body) {
        var command = of(body).map(Commands.UpdateExerciseCommand::valueOf).get();
        var updatedExercise = exerciseService.update(id, command);
        return ok(updatedExercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exerciseService.delete(id);
        return ok().build();
    }
}
