package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.dto.command.Commands.CreateCommentCommand;
import com.arturfrimu.studies.dto.command.Commands.UpdateCommentCommand;
import com.arturfrimu.studies.dto.request.Requests.CreateCommentRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateCommentRequest;
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

import static java.util.Optional.of;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> list() {
        var comments = commentService.list();
        return ok(comments);
    }

    @GetMapping("/{id}")
    ResponseEntity<Comment> find(@PathVariable Long id) {
        var comment = commentService.find(id);
        return ok(comment);
    }

    @PostMapping
    ResponseEntity<Comment> create(@RequestBody CreateCommentRequest body) {
        var command = of(body).map(CreateCommentCommand::valueOf).get();
        var createdComment = commentService.create(command);
        return ok(createdComment);
    }

    @PutMapping("/{id}")
    ResponseEntity<Comment> update(@PathVariable Long id, @RequestBody UpdateCommentRequest body) {
        var command = of(body).map(UpdateCommentCommand::valueOf).get();
        var updatedComment = commentService.update(id, command);
        return ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ok().build();
    }
}
