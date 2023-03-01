package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.entity.Task;
import com.arturfrimu.studies.service.TaskService;
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
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<Task> list() {
        return taskService.list();
    }

    @GetMapping("/{id}")
    public Task find(@PathVariable Long id) {
        return taskService.find(id);
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.create(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        return taskService.update(id, task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ok().build();
    }
}
