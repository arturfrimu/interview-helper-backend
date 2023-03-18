package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.entity.Forum;
import com.arturfrimu.interview.helper.repository.ForumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.arturfrimu.interview.helper.exception.ExceptionContainer.ResourceNotFoundException;
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

    public Forum create(Commands.CreateForumCommand command) {
        var newForum = new Forum(command.name(), command.description());
        return forumRepository.save(newForum);
    }

    public Forum update(Long id, Commands.UpdateForumCommand command) {
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
