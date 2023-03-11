package com.arturfrimu.studies.service;

import com.arturfrimu.studies.dto.command.Commands.CreateCommentCommand;
import com.arturfrimu.studies.dto.command.Commands.UpdateCommentCommand;
import com.arturfrimu.studies.entity.Comment;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.CommentRepository;
import com.arturfrimu.studies.repository.PostRepository;
import com.arturfrimu.studies.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<Comment> list() {
        return commentRepository.findAll();
    }

    public Comment find(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Comment not found with id: %s", id)));
    }

    public Comment create(CreateCommentCommand command) {
        var existingUser = userRepository.findById(command.userId())
                .orElseThrow(() -> new ResourceNotFoundException(format("User not found with id: %s", command.userId())));

        var existingPost = postRepository.findById(command.postId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Post not found with id: %s", command.postId())));

        var newComment = new Comment(command.contend(), existingPost, existingUser);

        return commentRepository.save(newComment);
    }

    public Comment update(Long id, UpdateCommentCommand command) {
        var existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Comment not found with id: %s", id)));

        var existingUser = userRepository.findById(command.userId())
                .orElseThrow(() -> new ResourceNotFoundException(format("User not found with id: %s", command.userId())));

        var existingPost = postRepository.findById(command.postId())
                .orElseThrow(() -> new ResourceNotFoundException(format("Post not found with id: %s", command.postId())));


        existingComment.setContent(command.content());
        existingComment.setPost(existingPost);
        existingComment.setUser(existingUser);

        return commentRepository.save(existingComment);
    }

    public void delete(Long id) {
        var existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Comment not found with id: %s", id)));

        commentRepository.delete(existingComment);
    }
}
