package com.arturfrimu.interview.helper.repository;

import com.arturfrimu.interview.helper.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
