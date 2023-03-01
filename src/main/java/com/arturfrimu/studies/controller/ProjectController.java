package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.entity.Project;
import com.arturfrimu.studies.service.ProjectService;
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
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> list() {
        var projects = projectService.list();
        return ok().body(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> find(@PathVariable("id") Long projectId) {
        var project = projectService.find(projectId);
        return ok().body(project);
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        var createdProject = projectService.create(project);
        return ok(createdProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@PathVariable("id") Long id, @RequestBody Project project) {
        var updatedProject = projectService.update(id, project);
        return ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        projectService.delete(id);
        return ok().build();
    }
}
