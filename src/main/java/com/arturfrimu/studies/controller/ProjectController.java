package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.dto.command.Commands.CreateProjectCommand;
import com.arturfrimu.studies.dto.command.Commands.UpdateProjectCommand;
import com.arturfrimu.studies.dto.request.Requests.CreateProjectRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateProjectRequest;
import com.arturfrimu.studies.dto.response.Response.ProjectDetailsResponse;
import com.arturfrimu.studies.dto.response.Response.ProjectInfoResponse;
import com.arturfrimu.studies.service.ProjectService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectInfoResponse>> list() {
        var projects = projectService.list();
        return ok().body(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDetailsResponse> find(@PathVariable("id") Long projectId) {
        var project = projectService.find(projectId);
        return ok().body(project);
    }

    @PostMapping
    public ResponseEntity<ProjectInfoResponse> create(@RequestBody @Valid CreateProjectRequest body) {
        var command = of(body).map(CreateProjectCommand::valueOf).get();
        var createdProject = projectService.create(command);
        return ok(createdProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectInfoResponse> update(@PathVariable("id") Long id, @RequestBody UpdateProjectRequest body) {
        var command = of(body).map(UpdateProjectCommand::valueOf).get();
        var updatedProject = projectService.update(id, command);
        return ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        projectService.delete(id);
        return ok().build();
    }
}
