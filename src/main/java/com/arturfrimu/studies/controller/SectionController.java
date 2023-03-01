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
@RequestMapping("/api/section")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @GetMapping("/sections")
    public List<Section> getAllSections() {
        return sectionService.getAllSections();
    }

    @GetMapping("/sections/{id}")
    public Section getSectionById(@PathVariable("id") Long id) {
        return sectionService.getSectionById(id);
    }

    @PostMapping("/sections")
    public Section createSection(@RequestBody Section section) {
        return sectionService.createSection(section);
    }

    @PutMapping("/sections/{id}")
    public Section updateSection(@PathVariable("id") Long id,
                                 @RequestBody Section sectionDetails) {
        return sectionService.updateSection(id, sectionDetails);
    }

    @DeleteMapping("/sections/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable("id") Long id) {
        sectionService.deleteSection(id);
        return ok().build();
    }
}
