package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.entity.Lesson;
import com.arturfrimu.interview.helper.exception.ExceptionContainer;
import com.arturfrimu.interview.helper.repository.LessonRepository;
import com.arturfrimu.interview.helper.repository.TopicRepository;
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
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Lesson not found with id: %s", id)));
    }

    public Lesson create(Commands.CreateLessonCommand command) {
        var existingTopic = topicRepository.findById(command.topicId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Topic not found with id: %s", command.topicId())));

        var newLesson = new Lesson(command.name(), command.description(), existingTopic);

        return lessonRepository.save(newLesson);
    }

    public Lesson update(Long id, Commands.UpdateLessonCommand command) {
        var existingLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Lesson not found with id: %s", id)));

        var existingTopic = topicRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Topic not found with id: %s", id)));

        existingLesson.setName(command.name());
        existingLesson.setDescription(command.description());
        existingLesson.setTopic(existingTopic);

        return lessonRepository.save(existingLesson);
    }

    public void delete(Long id) {
        var existingLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Lesson not found with id: %s", id)));

        lessonRepository.delete(existingLesson);
    }
}
