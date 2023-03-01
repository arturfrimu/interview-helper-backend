package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.entity.Chapter;
import com.arturfrimu.studies.service.ChapterService;
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
@RequestMapping("/api/chapters")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;

    @GetMapping
    public ResponseEntity<List<Chapter>> list() {
        var chapters = chapterService.list();
        return ok(chapters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chapter> find(@PathVariable Long id) {
        var chapter = chapterService.find(id);
        return ok(chapter);
    }

    @PostMapping
    public ResponseEntity<Chapter> create(@RequestBody Chapter chapter) {
        var createdChapter = chapterService.create(chapter);
        return ok(createdChapter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chapter> update(@PathVariable Long id, @RequestBody Chapter update) {
        var updatedChapter = chapterService.update(id, update);
        return ok(updatedChapter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        chapterService.delete(id);
        return ok().build();
    }
}
