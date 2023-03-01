package com.arturfrimu.studies.service;

import com.arturfrimu.studies.entity.Comment;
import com.arturfrimu.studies.exception.ResourceNotFoundException;
import com.arturfrimu.studies.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> list() {
        return commentRepository.findAll();
    }

    public Comment find(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Comment not found with id: %s", id)));
    }

    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment update(Long id, Comment command) {
        var existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Comment not found with id: %s", id)));

        existingComment.setContent(command.getContent());
        existingComment.setPost(command.getPost());
        existingComment.setUser(command.getUser());

        return commentRepository.save(existingComment);
    }

    public void delete(Long id) {
        var existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Comment not found with id: %s", id)));
        commentRepository.delete(existingComment);
    }
}
