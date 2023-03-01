package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.entity.Comment;
import com.arturfrimu.studies.service.CommentService;
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
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<Comment> list() {
        return commentService.list();
    }

    @GetMapping("/{id}")
    public Comment find(@PathVariable Long id) {
        return commentService.find(id);
    }

    @PostMapping
    public Comment create(@RequestBody Comment comment) {
        return commentService.create(comment);
    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable Long id, @RequestBody Comment comment) {
        return commentService.update(id, comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ok().build();
    }
}
