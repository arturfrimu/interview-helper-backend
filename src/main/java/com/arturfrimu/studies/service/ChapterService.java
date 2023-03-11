package com.arturfrimu.studies.service;

import com.arturfrimu.studies.dto.command.Commands.CreateChapterCommand;
import com.arturfrimu.studies.dto.command.Commands.UpdateChapterCommand;
import com.arturfrimu.studies.entity.Chapter;
import com.arturfrimu.studies.exception.ExceptionContainer.ResourceNotFoundException;
import com.arturfrimu.studies.repository.ChapterRepository;
import com.arturfrimu.studies.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ChapterService {

    private final ChapterRepository chapterRepository;
    private final CourseRepository courseRepository;

    public List<Chapter> list() {
        return chapterRepository.findAll();
    }

    public Chapter find(Long id) {
        return chapterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Chapter not found with id: %s", id)));
    }

    public Chapter create(CreateChapterCommand command) {
        var existingCourse = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Course not found with id: %s", command.courseId())));

        var newChapter = new Chapter(command.name(), command.description(), existingCourse);

        return chapterRepository.save(newChapter);
    }

    public Chapter update(Long id, UpdateChapterCommand command) {
        var existingChapter = chapterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Chapter not found with id: %s", id)));

        var existingCourse = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Course not found with id: %s", command.courseId())));

        existingChapter.setName(command.name());
        existingChapter.setDescription(command.description());
        existingChapter.setCourse(existingCourse);

        return chapterRepository.save(existingChapter);
    }

    public void delete(Long id) {
        var chapter = chapterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Chapter not found with id: %s", id)));

        chapterRepository.delete(chapter);
    }
}
