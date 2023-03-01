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

@RestController
@RequestMapping("/api/forum")
@RequiredArgsConstructor
public class ForumController {

    private final ForumService forumService;

    @GetMapping("")
    public List<Forum> getAllForums() {
        return forumService.getAllForums();
    }

    @GetMapping("/{id}")
    public Forum getForumById(@PathVariable Long id) {
        return forumService.getForumById(id);
    }

    @PostMapping("")
    public Forum createForum(@RequestBody Forum forum) {
        return forumService.createForum(forum);
    }

    @PutMapping("/{id}")
    public Forum updateForum(@PathVariable Long id, @RequestBody Forum forum) {
        return forumService.updateForum(id, forum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForum(@PathVariable Long id) {
        Boolean deleted = forumService.deleteForum(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
