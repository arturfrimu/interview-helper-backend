package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Lesson;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    public List<Lesson> list() {
        return lessonRepository.findAll();
    }

    public Lesson find(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Lesson not found with id: %s", id)));
    }

    public void create(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public void update(Long id, Lesson lesson) {
        var existingLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Lesson not found with id: %s", id)));

        existingLesson.setName(lesson.getName());
        existingLesson.setDescription(lesson.getDescription());

        lessonRepository.save(existingLesson);
    }

    public void delete(Long id) {
        var existingLesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Lesson not found with id: %s", id)));
        lessonRepository.delete(existingLesson);
    }
}
