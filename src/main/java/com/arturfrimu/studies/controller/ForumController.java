package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.entity.Forum;
import com.arturfrimu.studies.service.ForumService;
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
@RequestMapping("/api/forums")
@RequiredArgsConstructor
public class ForumController {

    private final ForumService forumService;

    @GetMapping
    public List<Forum> list() {
        return forumService.list();
    }

    @GetMapping("/{id}")
    public Forum find(@PathVariable Long id) {
        return forumService.find(id);
    }

    @PostMapping
    public Forum create(@RequestBody Forum forum) {
        return forumService.create(forum);
    }

    @PutMapping("/{id}")
    public Forum update(@PathVariable Long id, @RequestBody Forum forum) {
        return forumService.update(id, forum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        forumService.delete(id);
        return ok().build();
    }
}
