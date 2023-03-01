package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Project;
import com.arturfrimu.studies.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long projectId, Project projectDetails) {
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

    public boolean deleteProject(Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            projectRepository.delete(project);
            return true;
        }
        return false;
    }
}
