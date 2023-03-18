package com.arturfrimu.interview.helper.repository;

import com.arturfrimu.interview.helper.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
}
