package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Task;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> list() {
        return taskRepository.findAll();
    }

    public Task find(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Long id, Task task) {
        Task existingTask = find(id);
        existingTask.setName(task.getName());
        existingTask.setDescription(task.getDescription());
        existingTask.setProject(task.getProject());
        return taskRepository.save(existingTask);
    }

    public void delete(Long id) {
        var existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Task not found with id: %s", id)));
        taskRepository.delete(existingTask);
    }
}
