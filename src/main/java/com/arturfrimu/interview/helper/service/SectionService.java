package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.entity.Section;
import com.arturfrimu.interview.helper.exception.ExceptionContainer;
import com.arturfrimu.interview.helper.repository.ChapterRepository;
import com.arturfrimu.interview.helper.repository.CourseRepository;
import com.arturfrimu.interview.helper.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    private final ChapterRepository chapterRepository;

    public List<Section> list() {
        return sectionRepository.findAll();
    }

    public Section find(Long id) {
        return sectionRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Section not found with id: %s", id)));
    }

    public Section create(Commands.CreateSectionCommand command) {
        var existingCourse = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Course not found with id: %s", command.courseId())));

        var existingChapter = chapterRepository.findById(command.chapterId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Chapter not found with id: %s", command.chapterId())));

        var newSection = new Section(command.name(), command.description(), existingCourse, existingChapter);

        return sectionRepository.save(newSection);
    }

    public Section update(Long id, Commands.UpdateSectionCommand command) {
        var existingSection = sectionRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Section not found with id: %s", id)));

        var existingCourse = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Course not found with id: %s", command.courseId())));

        var existingChapter = chapterRepository.findById(command.chapterId())
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Chapter not found with id: %s", command.chapterId())));

        existingSection.setName(command.name());
        existingSection.setDescription(command.description());
        existingSection.setCourse(existingCourse);
        existingSection.setChapter(existingChapter);

        return sectionRepository.save(existingSection);
    }

    public void delete(Long id) {
        var existingSection = sectionRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Section not found with id: %s", id)));

        sectionRepository.delete(existingSection);
    }
}
