package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Section;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class SectionService {
    private final SectionRepository sectionRepository;

    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    public Section getSectionById(Long id) {
        return sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section"));
    }

    public Section createSection(Section section) {
        return sectionRepository.save(section);
    }

    public Section updateSection(Long id, Section sectionDetails) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section"));

        section.setName(sectionDetails.getName());
        section.setDescription(sectionDetails.getDescription());
        section.setCourse(sectionDetails.getCourse());
        section.setChapter(sectionDetails.getChapter());

        return sectionRepository.save(section);
    }

    public void deleteSection(Long id) {
        var existingSection = sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Section not found with id: %s", id)));
        sectionRepository.delete(existingSection);
    }
}
