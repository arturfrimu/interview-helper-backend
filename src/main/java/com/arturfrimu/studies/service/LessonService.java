package com.arturfrimu.studies.service;

import com.arturfrimu.studies.dto.command.Commands.CreateLessonCommand;
import com.arturfrimu.studies.dto.command.Commands.UpdateLessonCommand;
import com.arturfrimu.studies.entity.Lesson;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.LessonRepository;
import com.arturfrimu.studies.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final TopicRepository topicRepository;

    public List<Lesson> list() {
        return lessonRepository.findAll();
    }

    public Lesson find(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Lesson not found with id: %s", id)));
    }

    public Lesson create(CreateLessonCommand command) {
        var existingTopic = topicRepository.findById(command.topicId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Topic not found with id: %s", command.topicId())));

        var newLesson = new Lesson(command.name(), command.description(), existingTopic);

        return lessonRepository.save(newLesson);
    }

    public Lesson update(Long id, UpdateLessonCommand command) {
        var existingLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Lesson not found with id: %s", id)));

        var existingTopic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Topic not found with id: %s", id)));

        existingLesson.setName(command.name());
        existingLesson.setDescription(command.description());
        existingLesson.setTopic(existingTopic);

        return lessonRepository.save(existingLesson);
    }

    public void delete(Long id) {
        var existingLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Lesson not found with id: %s", id)));

        lessonRepository.delete(existingLesson);
    }
}
