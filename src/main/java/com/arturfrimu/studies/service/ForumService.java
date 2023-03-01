package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Forum;
import com.arturfrimu.studies.repository.ForumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumService {
    private final ForumRepository forumRepository;

    public List<Forum> getAllForums() {
        return forumRepository.findAll();
    }

    public Forum getForumById(Long id) {
        return forumRepository.findById(id).orElse(null);
    }

    public Forum createForum(Forum forum) {
        return forumRepository.save(forum);
    }

    public Forum updateForum(Long id, Forum forum) {
        Forum existingForum = forumRepository.findById(id).orElse(null);
        if (existingForum == null) {
            return null;
        }
        existingForum.setName(forum.getName());
        existingForum.setDescription(forum.getDescription());
        return forumRepository.save(existingForum);
    }

    public Boolean deleteForum(Long id) {
        Forum existingForum = forumRepository.findById(id).orElse(null);
        if (existingForum == null) {
            return false;
        }
        forumRepository.delete(existingForum);
        return true;
    }
}
