package com.arturfrimu.interview.helper.repository;

import com.arturfrimu.interview.helper.entity.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Long> {
}
