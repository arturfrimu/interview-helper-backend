package com.arturfrimu.studies.service;

import com.arturfrimu.studies.dto.command.Commands.CreateTopicCommand;
import com.arturfrimu.studies.dto.command.Commands.UpdateTopicCommand;
import com.arturfrimu.studies.entity.Topic;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    public List<Topic> list() {
        return topicRepository.findAll();
    }

    public Topic find(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Topic not found with id: %s", id)));
    }

    public Topic create(CreateTopicCommand command) {
        var newTopic = new Topic(command.name(), command.description());
        return topicRepository.save(newTopic);
    }

    public Topic update(Long id, UpdateTopicCommand command) {
        var existingTopic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Topic not found with id: %s", id)));

        existingTopic.setName(command.name());
        existingTopic.setDescription(command.description());

        return topicRepository.save(existingTopic);
    }

    public void delete(Long id) {
        var existingTopic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Topic not found with id: %s", id)));
        topicRepository.delete(existingTopic);
    }
}
