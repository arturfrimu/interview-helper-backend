package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Lesson;
import com.arturfrimu.studies.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Lesson getLessonById(Long id) {
        Optional<Lesson> optionalLesson = lessonRepository.findById(id);
        return optionalLesson.orElse(null);
    }

    public void addLesson(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public void updateLesson(Long id, Lesson lesson) {
        Optional<Lesson> optionalLesson = lessonRepository.findById(id);
        if (optionalLesson.isPresent()) {
            Lesson existingLesson = optionalLesson.get();
            existingLesson.setName(lesson.getName());
            existingLesson.setDescription(lesson.getDescription());
            lessonRepository.save(existingLesson);
        }
    }

    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }
}
