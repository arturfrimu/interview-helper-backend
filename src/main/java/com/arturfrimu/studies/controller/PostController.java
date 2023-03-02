package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.dto.command.Commands.CreatePostCommand;
import com.arturfrimu.studies.dto.command.Commands.UpdatePostCommand;
import com.arturfrimu.studies.dto.request.Requests.CreatePostRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdatePostRequest;
import com.arturfrimu.studies.entity.Post;
import com.arturfrimu.studies.service.PostService;
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
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> list() {
        var posts = postService.list();
        return ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> find(@PathVariable Long id) {
        var post = postService.find(id);
        return ok(post);
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody CreatePostRequest body) {
        var command = of(body).map(CreatePostCommand::valueOf).get();
        var createdPost = postService.create(command);
        return ok(createdPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody UpdatePostRequest body) {
        var command = of(body).map(UpdatePostCommand::valueOf).get();
        var updatedPost = postService.update(id, command);
        return ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return ok().build();
    }
}
