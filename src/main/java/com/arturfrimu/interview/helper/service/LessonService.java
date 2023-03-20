package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.dto.command.Commands.CreateLessonCommand;
import com.arturfrimu.interview.helper.dto.command.Commands.UpdateLessonCommand;
import com.arturfrimu.interview.helper.dto.response.Response.LessonInfoResponse;
import com.arturfrimu.interview.helper.entity.Lesson;
import com.arturfrimu.interview.helper.exception.ExceptionContainer.ResourceNotFoundException;
import com.arturfrimu.interview.helper.repository.LessonRepository;
import com.arturfrimu.interview.helper.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final SectionRepository sectionRepository;

    public List<LessonInfoResponse> list() {
        return lessonRepository.findAll().stream().map(LessonInfoResponse::valueOf).toList();
    }

    public LessonInfoResponse find(Long id) {
        return lessonRepository.findById(id).map(LessonInfoResponse::valueOf)
                .orElseThrow(() -> new ResourceNotFoundException(format("Lesson not found with id: %s", id)));
    }

    public LessonInfoResponse create(CreateLessonCommand command) {
        var existingSection = sectionRepository.findById(command.topicId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Section not found with id: %s", command.topicId())));

        var newLesson = new Lesson(command.name(), command.description(), existingSection);

        return LessonInfoResponse.valueOf(lessonRepository.save(newLesson));
    }

    public LessonInfoResponse update(Long id, UpdateLessonCommand command) {
        var existingLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Lesson not found with id: %s", id)));

        var existingSection = sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Section not found with id: %s", id)));

        var newLesson = new Lesson(existingLesson.getLessonId(), command.name(), command.description(), existingSection);

        return LessonInfoResponse.valueOf(lessonRepository.save(newLesson));
    }

    public void delete(Long id) {
        var existingLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Lesson not found with id: %s", id)));

        lessonRepository.delete(existingLesson);
    }
}
