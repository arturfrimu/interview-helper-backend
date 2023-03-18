package com.arturfrimu.interview.helper.repository;

import com.arturfrimu.interview.helper.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
