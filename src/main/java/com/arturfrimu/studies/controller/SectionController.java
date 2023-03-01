package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.entity.Section;
import com.arturfrimu.studies.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/sections")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @GetMapping("/sections")
    public ResponseEntity<List<Section>> getAllSections() {
        var sections = sectionService.getAllSections();
        return ok(sections);
    }

    @GetMapping("/sections/{id}")
    public ResponseEntity<Section> getSectionById(@PathVariable("id") Long id) {
        var section = sectionService.getSectionById(id);
        return ok(section);
    }

    @PostMapping("/sections")
    public ResponseEntity<Section> createSection(@RequestBody Section section) {
        var createdSection = sectionService.createSection(section);
        return ok(createdSection);
    }

    @PutMapping("/sections/{id}")
    public ResponseEntity<Section> updateSection(@PathVariable("id") Long id, @RequestBody Section section) {
        var updatedSection = sectionService.updateSection(id, section);
        return ok(updatedSection);
    }

    @DeleteMapping("/sections/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable("id") Long id) {
        sectionService.deleteSection(id);
        return ok().build();
    }
}
