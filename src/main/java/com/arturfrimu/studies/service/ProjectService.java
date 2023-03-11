package com.arturfrimu.studies.service;

import com.arturfrimu.studies.dto.command.Commands.CreateProjectCommand;
import com.arturfrimu.studies.dto.command.Commands.UpdateProjectCommand;
import com.arturfrimu.studies.entity.Project;
import com.arturfrimu.studies.exception.ExceptionContainer.ResourceNotFoundException;
import com.arturfrimu.studies.repository.CourseRepository;
import com.arturfrimu.studies.repository.ProjectRepository;
import com.arturfrimu.studies.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final CourseRepository courseRepository;
    private final SectionRepository sectionRepository;

    public List<Project> list() {
        return projectRepository.findAll();
    }

    public Project find(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Project not found with id: %s", id)));
    }

    public Project create(CreateProjectCommand command) {
        var existingCourse = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Course not found with id: %s", command.courseId())));

        var existingSection = sectionRepository.findById(command.sectionId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Section not found with id: %s", command.sectionId())));

        var newProject = new Project(command.name(), command.description(), existingCourse, existingSection);

        return projectRepository.save(newProject);
    }

    public Project update(Long id, UpdateProjectCommand command) {
        var existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Project not found with id: %s", id)));

        var existingCourse = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Course not found with id: %s", command.courseId())));

        var existingSection = sectionRepository.findById(command.sectionId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Section not found with id: %s", command.sectionId())));

        existingProject.setName(command.name());
        existingProject.setDescription(command.description());
        existingProject.setCourse(existingCourse);
        existingProject.setSection(existingSection);

        return projectRepository.save(existingProject);
    }

    public void delete(Long id) {
        var project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Project not found with id: %s", id)));

        projectRepository.delete(project);
    }
}
