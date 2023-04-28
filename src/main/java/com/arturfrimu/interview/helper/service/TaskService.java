package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.dto.command.Commands.CreateTaskCommand;
import com.arturfrimu.interview.helper.dto.command.Commands.UpdateTaskCommand;
import com.arturfrimu.interview.helper.dto.response.Response;
import com.arturfrimu.interview.helper.dto.response.Response.TaskInfoResponse;
import com.arturfrimu.interview.helper.entity.Task;
import com.arturfrimu.interview.helper.exception.ExceptionContainer;
import com.arturfrimu.interview.helper.repository.LessonRepository;
import com.arturfrimu.interview.helper.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;

    public List<TaskInfoResponse> list() {
        return taskRepository.findAll().stream().map(TaskInfoResponse::valueOf).toList();
    }

    public TaskInfoResponse find(Long id) {
        return taskRepository.findById(id).map(TaskInfoResponse::valueOf)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Task not found with id: %s", id)));
    }

    public TaskInfoResponse create(CreateTaskCommand command) {
        var existingLesson = lessonRepository.findById(command.lessonId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Lesson not found with id: %s", command.lessonId())));

        var newTask = new Task(command.name(), command.description(), existingLesson);

        return TaskInfoResponse.valueOf(taskRepository.save(newTask));
    }

    public TaskInfoResponse update(Long id, UpdateTaskCommand command) {
        var existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Task not found with id: %s", id)));

        var existingLesson = lessonRepository.findById(command.lessonId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Lesson not found with id: %s", command.lessonId())));

        var newTask = new Task(existingTask.getTaskId(), command.name(), command.description(), existingLesson);

        return TaskInfoResponse.valueOf(taskRepository.save(newTask));
    }

    public void delete(Long id) {
        var existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Task not found with id: %s", id)));

        taskRepository.delete(existingTask);
    }
}
