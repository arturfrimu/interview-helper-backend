package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.dto.command.Commands.CreateForumCommand;
import com.arturfrimu.studies.dto.command.Commands.UpdateForumCommand;
import com.arturfrimu.studies.dto.request.Requests.CreateForumRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateForumRequest;
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

import static java.util.Optional.of;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/forums")
@RequiredArgsConstructor
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
        var command = of(body).map(CreateForumCommand::valueOf).get();
        var createdForum = forumService.create(command);
        return ok(createdForum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Forum> update(@PathVariable Long id, @RequestBody UpdateForumRequest body) {
        var command = of(body).map(UpdateForumCommand::valueOf).get();
        var updatedForum = forumService.update(id, command);
        return ok(updatedForum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        forumService.delete(id);
        return ok().build();
    }
}
