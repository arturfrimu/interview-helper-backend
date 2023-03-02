package com.arturfrimu.studies.controller;

import com.arturfrimu.studies.dto.command.Commands;
import com.arturfrimu.studies.dto.command.Commands.CreateTopicCommand;
import com.arturfrimu.studies.dto.request.Requests.CreateTopicRequest;
import com.arturfrimu.studies.dto.request.Requests.UpdateTopicRequest;
import com.arturfrimu.studies.entity.Topic;
import com.arturfrimu.studies.service.TopicService;
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
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @GetMapping
    public ResponseEntity<List<Topic>> list() {
        var topics = topicService.list();
        return ok(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> find(@PathVariable Long id) {
        var topic = topicService.find(id);
        return ok(topic);
    }

    @PostMapping
    public ResponseEntity<Topic> create(@RequestBody CreateTopicRequest body) {
        var command = of(body).map(CreateTopicCommand::valueOf).get();
        var createdTopic = topicService.create(command);
        return ok(createdTopic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> update(@PathVariable Long id, @RequestBody UpdateTopicRequest body) {
        var command = of(body).map(Commands.UpdateTopicCommand::valueOf).get();
        var updatedTopic = topicService.update(id, command);
        return ok(updatedTopic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        topicService.delete(id);
        return ok().build();
    }
}
