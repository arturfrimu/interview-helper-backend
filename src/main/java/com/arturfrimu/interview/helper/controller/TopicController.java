package com.arturfrimu.interview.helper.controller;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.dto.request.Requests.CreateTopicRequest;
import com.arturfrimu.interview.helper.dto.request.Requests.UpdateTopicRequest;
import com.arturfrimu.interview.helper.dto.response.Response.TopicInfoResponse;
import com.arturfrimu.interview.helper.service.TopicService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/topics")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TopicController {

    private final TopicService topicService;

    @GetMapping
    public ResponseEntity<List<TopicInfoResponse>> list() {
        var topics = topicService.list();
        return ok(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicInfoResponse> find(@PathVariable Long id) {
        var topic = topicService.find(id);
        return ok(topic);
    }

    @PostMapping
    public ResponseEntity<TopicInfoResponse> create(@RequestBody @Valid CreateTopicRequest body) {
        var command = of(body).map(Commands.CreateTopicCommand::valueOf).get();
        var createdTopic = topicService.create(command);
        return ok(createdTopic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicInfoResponse> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicRequest body) {
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
