package com.arturfrimu.studies.service;

import com.arturfrimu.studies.dto.command.Commands.CreateTopicCommand;
import com.arturfrimu.studies.dto.command.Commands.UpdateTopicCommand;
import com.arturfrimu.studies.dto.response.Response.TopicInfoResponse;
import com.arturfrimu.studies.entity.Topic;
import com.arturfrimu.studies.exception.ExceptionContainer.ResourceNotFoundException;
import com.arturfrimu.studies.repository.ProjectRepository;
import com.arturfrimu.studies.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final ProjectRepository projectRepository;

    public List<TopicInfoResponse> list() {
        return topicRepository.findAll().stream().map(TopicInfoResponse::valueOf).toList();
    }

    public TopicInfoResponse find(Long id) {
        return topicRepository.findById(id).map(TopicInfoResponse::valueOf)
                .orElseThrow(() -> new ResourceNotFoundException(format("Topic not found with id: %s", id)));
    }

    public TopicInfoResponse create(CreateTopicCommand command) {
        var existingProject = projectRepository.findById(command.projectId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Project not found with id: %s", command.projectId())));

        var newTopic = new Topic(command.name(), command.description(), existingProject);

        return TopicInfoResponse.valueOf(topicRepository.save(newTopic));
    }

    public TopicInfoResponse update(Long id, UpdateTopicCommand command) {
        var existingTopic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Topic not found with id: %s", id)));

        var existingProject = projectRepository.findById(command.projectId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Project not found with id: %s", command.projectId())));

        var updatedTopic = new Topic(existingTopic.getTopicId(), command.name(), command.description(), existingProject);

        return TopicInfoResponse.valueOf(topicRepository.save(updatedTopic));
    }

    public void delete(Long id) {
        var existingTopic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Topic not found with id: %s", id)));

        topicRepository.delete(existingTopic);
    }
}
