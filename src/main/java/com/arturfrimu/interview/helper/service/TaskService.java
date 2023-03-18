package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.entity.Task;
import com.arturfrimu.interview.helper.exception.ExceptionContainer;
import com.arturfrimu.interview.helper.repository.TaskRepository;
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
        return taskRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Task not found with id: %s", id)));
    }

    public Task create(Task command) {
        return taskRepository.save(command);
    }

    public Task update(Long id, Task command) {
        var existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Task not found with id: %s", id)));

        existingTask.setName(command.getName());
        existingTask.setDescription(command.getDescription());
        existingTask.setProject(command.getProject());

        return taskRepository.save(existingTask);
    }

    public void delete(Long id) {
        var existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Task not found with id: %s", id)));

        taskRepository.delete(existingTask);
    }
}
