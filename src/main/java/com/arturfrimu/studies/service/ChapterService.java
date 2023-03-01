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
        return chapterRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Chapter not found with id: " + id));
    }

    public Chapter create(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    public Chapter updateChapter(Long id, Chapter chapterDetails) {
        Chapter chapter = chapterRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Chapter not found with id: " + id));

        chapter.setName(chapterDetails.getName());
        chapter.setDescription(chapterDetails.getDescription());
        chapter.setCourse(chapterDetails.getCourse());

        return chapterRepository.save(chapter);
    }

    public void delete(Long id) {
        var chapter = chapterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Chapter not found with id: %s", id)));
        chapterRepository.delete(chapter);
    }
}
