package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Project;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> list() {
        return projectRepository.findAll();
    }

    public Project find(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Project not found with id: %s", id)));
    }

    public Project create(Project project) {
        return projectRepository.save(project);
    }

    public Project update(Long id, Project command) {
        var existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Project not found with id: %s", id)));

        existingProject.setName(command.getName());
        existingProject.setDescription(command.getDescription());
        existingProject.setCourse(command.getCourse());
        existingProject.setSection(command.getSection());

        return projectRepository.save(existingProject);
    }

    public void delete(Long id) {
        var project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Project not found with id: %s", id)));
        projectRepository.delete(project);
    }
}
