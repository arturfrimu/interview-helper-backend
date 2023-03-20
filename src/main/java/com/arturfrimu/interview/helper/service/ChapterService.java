package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.dto.command.Commands.CreateChapterCommand;
import com.arturfrimu.interview.helper.dto.command.Commands.UpdateChapterCommand;
import com.arturfrimu.interview.helper.dto.response.Response.ChapterInfoResponse;
import com.arturfrimu.interview.helper.entity.Chapter;
import com.arturfrimu.interview.helper.exception.ExceptionContainer.ResourceNotFoundException;
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

    public List<ChapterInfoResponse> list() {
        return chapterRepository.findAll().stream().map(ChapterInfoResponse::valueOf).toList();
    }

    public ChapterInfoResponse find(Long id) {
        return chapterRepository.findById(id).map(ChapterInfoResponse::valueOf)
                .orElseThrow(() -> new ResourceNotFoundException(format("Chapter not found with id: %s", id)));
    }

    public ChapterInfoResponse create(CreateChapterCommand command) {
        var existingCourse = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Course not found with id: %s", command.courseId())));

        var newChapter = new Chapter(command.name(), command.description(), existingCourse);

        return ChapterInfoResponse.valueOf(chapterRepository.save(newChapter));
    }

    public ChapterInfoResponse update(Long id, UpdateChapterCommand command) {
        var existingChapter = chapterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Chapter not found with id: %s", id)));

        var existingCourse = courseRepository.findById(command.courseId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Course not found with id: %s", command.courseId())));

        var newChapter = new Chapter(existingChapter.getChapterId(), command.name(), command.description(), existingCourse);

        return ChapterInfoResponse.valueOf(chapterRepository.save(newChapter));
    }

    public void delete(Long id) {
        var chapter = chapterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Chapter not found with id: %s", id)));

        chapterRepository.delete(chapter);
    }
}
