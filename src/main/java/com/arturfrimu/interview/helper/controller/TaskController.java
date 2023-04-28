package com.arturfrimu.interview.helper.controller;

import com.arturfrimu.interview.helper.dto.command.Commands.CreateTaskCommand;
import com.arturfrimu.interview.helper.dto.command.Commands.UpdateTaskCommand;
import com.arturfrimu.interview.helper.dto.request.Requests.CreateTaskRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.UpdateTaskRequest;
import com.arturfrimu.interview.helper.dto.response.Response.TaskInfoResponse;
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

import static java.util.Optional.of;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskInfoResponse>> list() {
        var tasks = taskService.list();
        return ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskInfoResponse> find(@PathVariable Long id) {
        var task = taskService.find(id);
        return ok(task);
    }

    @PostMapping
    public ResponseEntity<TaskInfoResponse> create(@RequestBody CreateTaskRequest body) {
        var command = of(body).map(CreateTaskCommand::valueOf).get();
        var createdTask = taskService.create(command);
        return ok(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskInfoResponse> update(@PathVariable Long id, @RequestBody UpdateTaskRequest body) {
        var command = of(body).map(UpdateTaskCommand::valueOf).get();
        var updatedTask = taskService.update(id, command);
        return ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ok().build();
    }
}
