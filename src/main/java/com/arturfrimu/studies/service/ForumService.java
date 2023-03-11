package com.arturfrimu.studies.service;

import com.arturfrimu.studies.dto.command.Commands.CreateForumCommand;
import com.arturfrimu.studies.dto.command.Commands.UpdateForumCommand;
import com.arturfrimu.studies.entity.Forum;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.ForumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ForumService {

    private final ForumRepository forumRepository;

    public List<Forum> list() {
        return forumRepository.findAll();
    }

    public Forum find(Long id) {
        return forumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Forum not found with id: %s", id)));
    }

    public Forum create(CreateForumCommand command) {
        var newForum = new Forum(command.name(), command.description());
        return forumRepository.save(newForum);
    }

    public Forum update(Long id, UpdateForumCommand command) {
        var existingForum = forumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Forum not found with id: %s", id)));

        existingForum.setName(command.name());
        existingForum.setDescription(command.description());

        return forumRepository.save(existingForum);
    }

    public void delete(Long id) {
        var existingForum = forumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Forum not found with id: %s", id)));

        forumRepository.delete(existingForum);
    }
}
