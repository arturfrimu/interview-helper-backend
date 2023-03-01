package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Topic;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;

    public List<Topic> list() {
        return topicRepository.findAll();
    }

    public Topic find(Long id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        return optionalTopic.orElse(null);
    }

    public Topic create(Topic topic) {
        return topicRepository.save(topic);
    }

    public Topic update(Long id, Topic topic) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isPresent()) {
//            topic.setId(id);
            return topicRepository.save(topic);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        var existingTopic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Topic not found with id: %s", id)));
        topicRepository.delete(existingTopic);
    }
}
