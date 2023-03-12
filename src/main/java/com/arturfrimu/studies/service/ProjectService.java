package com.arturfrimu.studies.service;

import com.arturfrimu.studies.dto.command.Commands.CreateProjectCommand;
import com.arturfrimu.studies.dto.command.Commands.UpdateProjectCommand;
import com.arturfrimu.studies.dto.response.Response.ProjectDetailsResponse;
import com.arturfrimu.studies.dto.response.Response.ProjectInfoResponse;
import com.arturfrimu.studies.entity.Project;
import com.arturfrimu.studies.exception.ExceptionContainer.ResourceNotFoundException;
import com.arturfrimu.studies.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<ProjectInfoResponse> list() {
        return projectRepository.findAll().stream().map(ProjectInfoResponse::valueOf).toList();
    }

    public ProjectDetailsResponse find(Long id) {
        return projectRepository.findById(id).map(ProjectDetailsResponse::valueOf)
                .orElseThrow(() -> new ResourceNotFoundException(format("Project not found with id: %s", id)));
    }

    public ProjectInfoResponse create(CreateProjectCommand command) {
        var newProject = new Project(command.name(), command.description());

        return Optional.of(projectRepository.save(newProject)).map(ProjectInfoResponse::valueOf).get();
    }

    public ProjectInfoResponse update(Long id, UpdateProjectCommand command) {
        var existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Project not found with id: %s", id)));

        var updatedProject = new Project(existingProject.getProjectId(), command.name(), command.description());

        return Optional.of(projectRepository.save(updatedProject)).map(ProjectInfoResponse::valueOf).get();
    }

    public void delete(Long id) {
        var project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Project not found with id: %s", id)));

        projectRepository.delete(project);
    }
}
