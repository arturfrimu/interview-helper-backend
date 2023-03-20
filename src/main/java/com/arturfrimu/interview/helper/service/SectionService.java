package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.dto.command.Commands.CreateSectionCommand;
import com.arturfrimu.interview.helper.dto.command.Commands.UpdateSectionCommand;
import com.arturfrimu.interview.helper.dto.response.Response;
import com.arturfrimu.interview.helper.dto.response.Response.SectionInfoResponse;
import com.arturfrimu.interview.helper.entity.Section;
import com.arturfrimu.interview.helper.exception.ExceptionContainer.ResourceNotFoundException;
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
    private final ChapterRepository chapterRepository;

    public List<SectionInfoResponse> list() {
        return sectionRepository.findAll().stream().map(Response.SectionInfoResponse::valueOf).toList();
    }

    public SectionInfoResponse find(Long id) {
        return sectionRepository.findById(id).map(Response.SectionInfoResponse::valueOf)
                .orElseThrow(() -> new ResourceNotFoundException(format("Section not found with id: %s", id)));
    }

    public SectionInfoResponse create(CreateSectionCommand command) {
        var existingChapter = chapterRepository.findById(command.chapterId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Chapter not found with id: %s", command.chapterId())));

        var newSection = new Section(command.name(), command.description(), existingChapter);

        return SectionInfoResponse.valueOf(sectionRepository.save(newSection));
    }

    public SectionInfoResponse update(Long id, UpdateSectionCommand command) {
        var existingSection = sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Section not found with id: %s", id)));

        var existingChapter = chapterRepository.findById(command.chapterId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Chapter not found with id: %s", command.chapterId())));

        var newSection = new Section(existingSection.getSectionId(), command.name(), command.description(), existingChapter);

        return SectionInfoResponse.valueOf(sectionRepository.save(newSection));
    }

    public void delete(Long id) {
        var existingSection = sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Section not found with id: %s", id)));

        sectionRepository.delete(existingSection);
    }
}
