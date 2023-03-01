package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Post;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> list() {
        return postRepository.findAll();
    }

    public Post find(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Post not found with id: %s", id)));
    }

    public Post create(Post post) {
        return postRepository.save(post);
    }

    public Post update(Long id, Post command) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Post not found with id: %s", id)));

        existingPost.setTitle(command.getTitle());
        existingPost.setContent(command.getContent());
        existingPost.setForum(command.getForum());

        return postRepository.save(existingPost);
    }

    public void delete(Long id) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Post not found with id: %s", id)));
        postRepository.delete(existingPost);
    }
}
