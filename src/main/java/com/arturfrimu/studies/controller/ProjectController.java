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
    public List<Project> list() {
        return projectService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> find(@PathVariable("id") Long projectId) {
        Project project = projectService.find(projectId);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(project);
    }

    @PostMapping
    public Project create(@RequestBody Project project) {
        return projectService.create(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@PathVariable("id") Long id,
                                          @RequestBody Project projectDetails) {
        Project updatedProject = projectService.update(id, projectDetails);
        if (updatedProject == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        projectService.delete(id);
        return ok().build();
    }
}
