package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Chapter;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ChapterService {

    private final ChapterRepository chapterRepository;

    public List<Chapter> list() {
        return chapterRepository.findAll();
    }

    public Chapter find(Long id) {
        return chapterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Chapter not found with id: %s", id)));
    }

    public Chapter create(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    public Chapter update(Long id, Chapter command) {
        var existingChapter = chapterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Chapter not found with id: %s", id)));

        existingChapter.setName(command.getName());
        existingChapter.setDescription(command.getDescription());
        existingChapter.setCourse(command.getCourse());

        return chapterRepository.save(existingChapter);
    }

    public void delete(Long id) {
        var chapter = chapterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Chapter not found with id: %s", id)));
        chapterRepository.delete(chapter);
    }
}
