package com.arturfrimu.interview.helper.controller;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.dto.request.Requests.CreateForumRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.UpdateForumRequest;
import com.arturfrimu.interview.helper.entity.Forum;
import com.arturfrimu.interview.helper.service.ForumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api/forums")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ForumController {

    private final ForumService forumService;

    @GetMapping
    public ResponseEntity<List<Forum>> list() {
        var forums = forumService.list();
        return ok(forums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Forum> find(@PathVariable Long id) {
        var forum = forumService.find(id);
        return ok(forum);
    }

    @PostMapping
    public ResponseEntity<Forum> create(@RequestBody CreateForumRequest body) {
        var command = of(body).map(Commands.CreateForumCommand::valueOf).get();
        var createdForum = forumService.create(command);
        return ok(createdForum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Forum> update(@PathVariable Long id, @RequestBody UpdateForumRequest body) {
        var command = of(body).map(Commands.UpdateForumCommand::valueOf).get();
        var updatedForum = forumService.update(id, command);
        return ok(updatedForum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        forumService.delete(id);
        return ok().build();
    }
}
