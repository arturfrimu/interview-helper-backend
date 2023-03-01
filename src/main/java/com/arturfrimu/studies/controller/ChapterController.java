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
    public List<Chapter> list() {
        return chapterService.list();
    }

    @GetMapping("/{id}")
    public Chapter find(@PathVariable Long id) {
        return chapterService.find(id);
    }

    @PostMapping
    public Chapter create(@RequestBody Chapter chapter) {
        return chapterService.create(chapter);
    }

    @PutMapping("/{id}")
    public Chapter update(@PathVariable Long id, @RequestBody Chapter update) {
        return chapterService.update(id, update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        chapterService.delete(id);
        return ok().build();
    }
}
