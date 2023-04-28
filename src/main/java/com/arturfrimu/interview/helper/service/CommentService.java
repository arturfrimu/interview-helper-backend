package com.arturfrimu.interview.helper.service;

import com.arturfrimu.interview.helper.dto.command.Commands;
import com.arturfrimu.interview.helper.entity.Comment;
import com.arturfrimu.interview.helper.exception.ExceptionContainer;
import com.arturfrimu.interview.helper.repository.CommentRepository;
import com.arturfrimu.interview.helper.repository.PostRepository;
import com.arturfrimu.interview.helper.repository.UserRepository;
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
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Comment not found with id: %s", id)));
    }

    public Comment create(Commands.CreateCommentCommand command) {
        var newComment = new Comment(command.contend());

        return commentRepository.save(newComment);
    }

    public Comment update(Long id, Commands.UpdateCommentCommand command) {
        var existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Comment not found with id: %s", id)));

        existingComment.setContent(command.content());

        return commentRepository.save(existingComment);
    }

    public void delete(Long id) {
        var existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new ExceptionContainer.ResourceNotFoundException(format("Comment not found with id: %s", id)));

        commentRepository.delete(existingComment);
    }
}
