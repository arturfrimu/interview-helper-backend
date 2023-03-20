package com.arturfrimu.interview.helper.controller;

import com.arturfrimu.interview.helper.entity.Task;
import com.arturfrimu.interview.helper.service.TaskService;
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

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> list() {
        var tasks = taskService.list();
        return ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> find(@PathVariable Long id) {
        var task = taskService.find(id);
        return ok(task);
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        var createdTask = taskService.create(task);
        return ok(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task) {
        var updatedTask = taskService.update(id, task);
        return ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ok().build();
    }
}
