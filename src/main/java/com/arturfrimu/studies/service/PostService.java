package com.arturfrimu.studies.service;

import com.arturfrimu.studies.dto.command.Commands.CreatePostCommand;
import com.arturfrimu.studies.dto.command.Commands.UpdatePostCommand;
import com.arturfrimu.studies.entity.Post;
import com.arturfrimu.studies.exception.ExceptionContainer.ResourceNotFoundException;
import com.arturfrimu.studies.repository.ForumRepository;
import com.arturfrimu.studies.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ForumRepository forumRepository;

    public List<Post> list() {
        return postRepository.findAll();
    }

    public Post find(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Post not found with id: %s", id)));
    }

    public Post create(CreatePostCommand command) {
        var forum = forumRepository.findById(command.forumId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Forum not found with id: %s", command.forumId())));

        var newPost = new Post(command.title(), command.content(), forum);

        return postRepository.save(newPost);
    }

    public Post update(Long id, UpdatePostCommand command) {
        var existingPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Post not found with id: %s", id)));

        var forum = forumRepository.findById(command.forumId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Forum not found with id: %s", command.forumId())));

        existingPost.setTitle(command.title());
        existingPost.setContent(command.content());
        existingPost.setForum(forum);

        return postRepository.save(existingPost);
    }

    public void delete(Long id) {
        var existingPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Post not found with id: %s", id)));

        postRepository.delete(existingPost);
    }
}
