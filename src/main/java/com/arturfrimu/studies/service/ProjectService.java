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

    public Project find(Long projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

    public Project create(Project project) {
        return projectRepository.save(project);
    }

    public Project update(Long projectId, Project projectDetails) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            project.setName(projectDetails.getName());
            project.setDescription(projectDetails.getDescription());
            project.setCourse(projectDetails.getCourse());
            project.setSection(projectDetails.getSection());
            return projectRepository.save(project);
        }
        return null;
    }

    public void delete(Long id) {
        var project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Project not found with id: %s", id)));
        projectRepository.delete(project);
    }
}
