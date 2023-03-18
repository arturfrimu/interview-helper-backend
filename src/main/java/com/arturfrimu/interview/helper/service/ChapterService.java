package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.entity.Chapter;
import com.arturfrimu.interview.helper.exception.ExceptionContainer;
import com.arturfrimu.interview.helper.repository.ChapterRepository;
import com.arturfrimu.interview.helper.repository.CourseRepository;
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
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Chapter not found with id: %s", id)));
    }

    public Chapter create(Commands.CreateChapterCommand command) {
        var existingCourse = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Course not found with id: %s", command.courseId())));

        var newChapter = new Chapter(command.name(), command.description(), existingCourse);

        return chapterRepository.save(newChapter);
    }

    public Chapter update(Long id, Commands.UpdateChapterCommand command) {
        var existingChapter = chapterRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Chapter not found with id: %s", id)));

        var existingCourse = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Course not found with id: %s", command.courseId())));

        existingChapter.setName(command.name());
        existingChapter.setDescription(command.description());
        existingChapter.setCourse(existingCourse);

        return chapterRepository.save(existingChapter);
    }

    public void delete(Long id) {
        var chapter = chapterRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Chapter not found with id: %s", id)));

        chapterRepository.delete(chapter);
    }
}
