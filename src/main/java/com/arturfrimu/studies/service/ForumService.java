package com.arturfrimu.studies.service;

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
        return forumRepository.findById(id).orElse(null);
    }

    public Forum create(Forum forum) {
        return forumRepository.save(forum);
    }

    public Forum update(Long id, Forum forum) {
        Forum existingForum = forumRepository.findById(id).orElse(null);
        if (existingForum == null) {
            return null;
        }
        existingForum.setName(forum.getName());
        existingForum.setDescription(forum.getDescription());
        return forumRepository.save(existingForum);
    }

    public void delete(Long id) {
        var existingForum = forumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Forum not found with id: %s", id)));
        forumRepository.delete(existingForum);
    }
}
